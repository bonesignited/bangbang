package com.zlx.bangbang.service;

import com.zlx.bangbang.domain.Address;
import com.zlx.bangbang.dto.AddressDTO;
import com.zlx.bangbang.enums.IndentTypeEnum;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface AddressService {
    /**
     * 添加用户常用地址
     */
    void addUsualAddress(String userId, AddressDTO addressDTO) throws AccessDeniedException;

    /**
     * 通过 id 获取address信息
     */
    Address getById(Integer addressId);

    /**
     * 获取所有常用地址
     */
    List<Address> getUsualAddress(String userId);

    List<Address> getAllByAddress(String userId, String address);

    /**
     * 修改地址
     */
    void modifyAddress(Integer addressId, String userId, AddressDTO modifyAddressDTO) throws AccessDeniedException;

    /**
     * 删除地址
     */
    void deleteAddress(Integer addressId, String userId) throws AccessDeniedException;

}

