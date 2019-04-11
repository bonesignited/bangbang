package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.Indent;
import com.zlx.bangbang.enums.GenderEnum;
import com.zlx.bangbang.enums.IndentStateEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IndentMapper {
    int deleteByPrimaryKey(Integer indentId);

    int insert(Indent record);

    int insertSelective(Indent record);

    Indent findByIndentId(Integer indentId);

    int updateByPrimaryKeySelective(Indent record);

    int updateByPrimaryKey(Indent record);

    // 根据承接者获得订单列表
    List<Indent> findByPerformerId(String userId);

    // 根据发布者获得订单列表
    List<Indent> findByPublisherId(String publisherId);

    // 根据订单状态获得订单列表，并排除某个性别
    List<Indent> findAllByIndentStateAndRequireGenderNot(@Param("state") IndentStateEnum state,
                                                         @Param("gender") GenderEnum excludeGender);

    // 根据订单状态获得订单列表，并排除某个性别，按赏金降序
    List<Indent> findAllByIndentStateAndRequireGenderNotOrderByIndentPriceDesc(@Param("state") IndentStateEnum state,
                                                             @Param("gender") GenderEnum excludeGender);

    // 由晚到早
    List<Indent> findAllByIndentStateAndRequireGenderNotOrderByCreateTimeDesc(@Param("state") IndentStateEnum state,
                                                                               @Param("gender") GenderEnum excludeGender);

    List<Indent> findAllByIndentStateOrderCreateTimeDesc(IndentStateEnum state);

    List<Indent> findAllByIsSolvedAndUrgentTypeGreaterThanOrderByCreateTimeDesc(Boolean isSolved, Integer type);

}