package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.WithdrawRequest;

import java.util.List;

public interface WithdrawRequestMapper {
    int deleteByPrimaryKey(Integer withdrawId);

    int insert(WithdrawRequest record);

    int insertSelective(WithdrawRequest record);

    WithdrawRequest selectByPrimaryKey(Integer withdrawId);

    int updateByPrimaryKeySelective(WithdrawRequest record);

    int updateByPrimaryKey(WithdrawRequest record);

    List<WithdrawRequest> findAllByUserId(String userId);

    List<WithdrawRequest> findAllByIsSolved(boolean isSolved);
}