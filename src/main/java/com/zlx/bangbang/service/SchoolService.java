package com.zlx.bangbang.service;

import com.zlx.bangbang.domain.School;

import java.util.List;

public interface SchoolService {
    /**
     * 通过 id 获得学校信息
     */
    School getById(Integer schoolId);

    /**
     * 通过关键字获取学校
     */
    List<School> getSchoolInFuzzyMatching(String school);

    /**
     * 插入学校信息
     */
    void save(School school);
}
