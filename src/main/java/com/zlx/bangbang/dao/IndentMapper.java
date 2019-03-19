package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.Indent;

public interface IndentMapper {
    int deleteByPrimaryKey(Integer indentId);

    int insert(Indent record);

    int insertSelective(Indent record);

    Indent selectByPrimaryKey(Integer indentId);

    int updateByPrimaryKeySelective(Indent record);

    int updateByPrimaryKey(Indent record);
}