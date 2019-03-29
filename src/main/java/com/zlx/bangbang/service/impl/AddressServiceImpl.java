package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.dao.AddressMapper;
import com.zlx.bangbang.dao.UserMapper;
import com.zlx.bangbang.domain.Address;
import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.dto.AddressDTO;
import com.zlx.bangbang.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public void addUsualAddress(String userId, AddressDTO addressDTO) throws AccessDeniedException {
        User user = userMapper.selectByPrimaryKey(userId);
        if (!addressDTO.getUserId().equals(userId)) {
            throw new AccessDeniedException("Access Denied");
        }
        if (user == null) {
            Address address = new Address();
            address.setUserId(userId);
            BeanUtils.copyProperties(addressDTO, address);
            addressMapper.insertSelective(address);
        }
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
    public void modifyAddress(Integer addressId, String userId, AddressDTO modifyAddressDTO) throws AccessDeniedException {
        Address address = addressMapper.selectByPrimaryKey(addressId);
        if (address == null) {
            return;
        }

        if (!address.getUserId().equals(userId)) throw new AccessDeniedException("Access Denied");

        if (modifyAddressDTO.getAddress() != null) address.setAddress(modifyAddressDTO.getAddress());
        if (modifyAddressDTO.getPhone() != null) address.setPhone(modifyAddressDTO.getPhone());
        if (modifyAddressDTO.getUserName() != null) address.setUserName(modifyAddressDTO.getUserName());
        addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public void deleteAddress(Integer addressId, String userId) throws AccessDeniedException {
        Address address = addressMapper.selectByPrimaryKey(addressId);
        if (address != null) {
            if (!address.getUserId().equals(userId)) throw new AccessDeniedException("Access Denied");
            if (userId.equals(address.getUserId())) {
                address.setIsDeleted(true);
                addressMapper.updateByPrimaryKeySelective(address);
            }
        }
    }
}
