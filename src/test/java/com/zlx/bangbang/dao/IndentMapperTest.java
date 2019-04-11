package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.Indent;
import com.zlx.bangbang.enums.GenderEnum;
import com.zlx.bangbang.enums.IndentStateEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IndentMapperTest {

    @Autowired
    private IndentMapper indentMapper;

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
    public void findByIndentId() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {

    }

    @Test
    public void findByPerformerId() {
        List<Indent> indents = indentMapper.findByPerformerId("eYMTZz");
        System.out.println(indents);
        for (Indent indent : indents) {
            System.out.println(indent.toString());
        }
    }

    @Test
    public void findByPublisherId() {
        List<Indent> indents = indentMapper.findByPublisherId("H35g88");
        for (Indent indent : indents) {
            System.out.println(indent.toString());
        }
    }

    @Test
    public void findAllByIndentStateAndRequireGenderNot() {
        List<Indent> indents = indentMapper.findAllByIndentStateAndRequireGenderNot(IndentStateEnum.COMPLETED,
                GenderEnum.FEMALE);
        for (Indent indent : indents) {
            System.out.println(indent.toString());
        }
    }

    @Test
    public void findAllByIndentStateAndRequireGenderNotOrderByIndentPriceDesc() {
        List<Indent> indents = indentMapper.findAllByIndentStateAndRequireGenderNotOrderByIndentPriceDesc(
                IndentStateEnum.ARRIVED,
                GenderEnum.FEMALE
        );
        for (Indent indent : indents) {
            System.out.println(indent.toString());
        }
    }

    @Test
    public void findAllByIndentStateAndRequireGenderNotOrderByCreateTimeDesc() {
        List<Indent> indents = indentMapper.findAllByIndentStateAndRequireGenderNotOrderByCreateTimeDesc(
                IndentStateEnum.ARRIVED,
                GenderEnum.FEMALE
        );
        for (Indent indent : indents) {
            System.out.println(indent.toString());
        }
    }

    @Test
    public void findAllByIndentStateOrderCreateTimeDesc() {
        List<Indent> indents = indentMapper.findAllByIndentStateOrderCreateTimeDesc(
                IndentStateEnum.ARRIVED
        );
        for (Indent indent : indents) {
            System.out.println(indent.toString());
        }
    }

    @Test
    public void findAllByIsSolvedAndUrgentTypeGreaterThanOrderByCreateTimeDesc() {

    }
}