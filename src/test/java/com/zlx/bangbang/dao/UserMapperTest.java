package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.enums.GenderEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

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
    public void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey("0UUMg4");
        System.out.println(user.getUserName());
    }

    @Test
    public void selectByUsername() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
        User record = new User();
        record.setId("A12345");
        record.setGender(GenderEnum.MALE);
        record.setBalance(BigDecimal.valueOf(10));
        int rows = userMapper.updateByPrimaryKeySelective(record);
        System.out.println(rows);
    }

    @Test
    public void updateByPrimaryKey() {
    }
}