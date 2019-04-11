package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.School;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolMapperTest {
    @Autowired
    private SchoolMapper schoolMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void findSchoolById() {
        School school = schoolMapper.findSchoolById(9);
        System.out.println(school.getSchoolName());
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void findBySchoolNameLike() {
        List<School> schools = schoolMapper.findBySchoolNameLike("%科技%");
        for (School school : schools) {
            System.out.println(school.getSchoolName());
        }
    }
}