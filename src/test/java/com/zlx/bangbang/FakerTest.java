package com.zlx.bangbang;

import com.github.javafaker.Faker;
import com.zlx.bangbang.dao.UserMapper;
import com.zlx.bangbang.domain.Indent;
import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.enums.GenderEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FakerTest {
    @Autowired
    private UserMapper userMapper;


    private static final String testUserId1 = "A12345";
    private static final String testUserId2 = "B12345";
    private static final String testUserId3 = "C12345";
    private static final String testUserId4 = "D12345";


    @Test
    public void insertIndent() {
        Indent indent = new Indent();
    }
}
