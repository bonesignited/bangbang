package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.WithdrawRequest;

public interface WithdrawRequestMapper {
    int deleteByPrimaryKey(Integer withdrawId);

    int insert(WithdrawRequest record);

    int insertSelective(WithdrawRequest record);

    WithdrawRequest selectByPrimaryKey(Integer withdrawId);

    int updateByPrimaryKeySelective(WithdrawRequest record);

    int updateByPrimaryKey(WithdrawRequest record);
}