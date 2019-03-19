package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.Config;

public interface ConfigMapper {
    int deleteByPrimaryKey(Boolean id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Boolean id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}