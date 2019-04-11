package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.dao.SchoolMapper;
import com.zlx.bangbang.domain.School;
import com.zlx.bangbang.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
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

    // 少一个新增学校，不过只有管理员能进行该操作
}
