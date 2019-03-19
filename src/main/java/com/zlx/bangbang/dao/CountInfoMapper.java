package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.CountInfo;

public interface CountInfoMapper {
    int deleteByPrimaryKey(Integer countId);

    int insert(CountInfo record);

    int insertSelective(CountInfo record);

    CountInfo selectByPrimaryKey(Integer countId);

    int updateByPrimaryKeySelective(CountInfo record);

    int updateByPrimaryKey(CountInfo record);
}