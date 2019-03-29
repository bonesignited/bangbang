package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.dao.SchoolMapper;
import com.zlx.bangbang.domain.School;
import com.zlx.bangbang.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public School getById(Integer schoolId) {
        return schoolMapper.findSchoolById(schoolId);
    }

    @Override
    public List<School> getSchoolInFuzzyMatching(String school) {
        return schoolMapper.findBySchoolNameLike("%" + school + "%");
    }

    @Override
    public void save(School school) {
        schoolMapper.updateByPrimaryKeySelective(school);
    }
}
