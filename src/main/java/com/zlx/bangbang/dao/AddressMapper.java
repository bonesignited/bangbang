package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AddressMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<Address> findAddressByUserIdAndIsDeletedIsFalse(String userId);

    List<Address> findAllByUserIdAndAddressLikeAndIsDeletedIsFalse(@Param("userId") String userId,
                                                                   @Param("address") String address);
}