package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SchoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(School record);

    int insertSelective(School record);

    School findSchoolById(Integer id);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);

    List<School> findBySchoolNameLike(String s);
}