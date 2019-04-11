package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.dao.IndentMapper;
import com.zlx.bangbang.domain.Indent;
import com.zlx.bangbang.enums.GenderEnum;
import com.zlx.bangbang.enums.IndentTypeEnum;
import com.zlx.bangbang.service.IndentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndentServiceImplTest {
    @Autowired
    private IndentService indentService;

    @Autowired
    private IndentMapper indentMapper;

    private Indent indent;

    @Before
    public void setUp() throws Exception {
        indent = Indent.builder().indentContent("测试").indentPrice(3).indentType(IndentTypeEnum.HELP_SEND)
                .secretText("测试隐私消息").requireGender(GenderEnum.NO_LIMITED).takeGoodAddress("测试取货地址")
                .shippingAddressId(71).publisherId("A12345").build();
    }

    @Test
    public void create() {
        indentService.create(indent);
    }

    @Test
    public void addIndentPrice() {
        String userId = "A12345";
        Integer indentId = 111;
        indentService.addIndentPrice(indentId, userId);
    }

    @Test
    public void takeIndent() {
        String performerId = "B12345";
        Integer indentId = 111;
        indentService.takeIndent(indentId, performerId);
    }

    @Test
    public void arrivedIndent() {
        String performerId = "B12345";
        Integer indentId = 111;
        indentService.arrivedIndent(indentId, performerId);
    }

    @Test
    public void finishedIndent() {
        String publisherId = "A12345";
        Integer indentId = 111;
        indentService.finishedIndent(indentId, publisherId);
    }

    @Test
    public void canceledIndent() {
        String publisherId = "A12345";
        Integer indentId = 112;
        indentService.canceledIndent(indentId, publisherId);
    }

    @Test
    public void getUserPublishedIndent() {
    }

    @Test
    public void getUserPerformedIndent() {
    }

    @Test
    public void getWait() {
    }

    @Test
    public void getIndentDetail() {
    }
}