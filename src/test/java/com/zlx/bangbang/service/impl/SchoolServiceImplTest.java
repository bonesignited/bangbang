package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.domain.School;
import com.zlx.bangbang.service.SchoolService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SchoolServiceImplTest {

    @Autowired
    private SchoolService schoolService;

    @Test
    public void getById() {
        School school = schoolService.getById(9);
        Assert.assertEquals("西安电子科技大学", school.getSchoolName());
    }

    @Test
    public void getSchoolInFuzzyMatching() {
        List<School> schools = schoolService.getSchoolInFuzzyMatching("西安");
        System.out.println(schools);
    }

    @Test
    public void save() {

    }
}