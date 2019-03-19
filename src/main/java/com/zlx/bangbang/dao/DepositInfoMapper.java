package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.DepositInfo;

public interface DepositInfoMapper {
    int deleteByPrimaryKey(String depositId);

    int insert(DepositInfo record);

    int insertSelective(DepositInfo record);

    DepositInfo selectByPrimaryKey(String depositId);

    int updateByPrimaryKeySelective(DepositInfo record);

    int updateByPrimaryKey(DepositInfo record);
}