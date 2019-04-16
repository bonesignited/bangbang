package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.dao.AddressMapper;
import com.zlx.bangbang.dao.UserMapper;
import com.zlx.bangbang.domain.Address;
import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.dto.AddressDTO;
import com.zlx.bangbang.exceptions.SubstituteException;
import com.zlx.bangbang.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public void addUsualAddress(String userId, AddressDTO addressDTO){
        log.info(userId + "将修改地址");
        User user = userMapper.selectByPrimaryKey(userId);
        if (!addressDTO.getUserId().equals(userId)) {
            throw new SubstituteException("用户Id不同，无法添加地址");
        }
        if (user != null) {
            Address address = new Address();
            address.setUserId(userId);
            BeanUtils.copyProperties(addressDTO, address);
            addressMapper.insertSelective(address);
        }
        log.info(userId + " 修改地址完毕，新地址为" + addressDTO.getAddress());
    }

    @Override
    public Address getById(Integer addressId) {
        if (addressId == null) {
            return null;
        }
        return addressMapper.selectByPrimaryKey(addressId);
    }

    @Override
    public List<Address> getUsualAddress(String userId) {
        return addressMapper.findAddressByUserIdAndIsDeletedIsFalse(userId);
    }

    @Override
    public List<Address> getAllByAddress(String userId, String address) {
        if (address == null) {
            address = "";
        }
        return addressMapper.findAllByUserIdAndAddressLikeAndIsDeletedIsFalse(userId, "%".concat(address).concat("%"));
    }

    @Override
    public void modifyAddress(Integer addressId, String userId, AddressDTO modifyAddressDTO){
        Address address = addressMapper.selectByPrimaryKey(addressId);
        if (address == null) {
            return;
        }

        if (!address.getUserId().equals(userId)) {
            throw new SubstituteException("用户Id不同，无法修改地址");
        }

        if (modifyAddressDTO.getAddress() != null) address.setAddress(modifyAddressDTO.getAddress());
        if (modifyAddressDTO.getPhone() != null) address.setPhone(modifyAddressDTO.getPhone());
        if (modifyAddressDTO.getUserName() != null) address.setUserName(modifyAddressDTO.getUserName());
        addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public void deleteAddress(Integer addressId, String userId){
        Address address = addressMapper.selectByPrimaryKey(addressId);
        if (address != null) {
            if (!address.getUserId().equals(userId)) {
                throw new SubstituteException("用户Id不同，无法删除地址");
            }
            if (userId.equals(address.getUserId())) {
                address.setIsDeleted(true);
                addressMapper.updateByPrimaryKeySelective(address);
            }
        }
    }
}
