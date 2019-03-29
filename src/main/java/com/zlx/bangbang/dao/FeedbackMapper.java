package com.zlx.bangbang.dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zlx.bangbang.domain.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FeedbackMapper {
    // todo 测试

    int deleteByPrimaryKey(Integer feedbackId);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer feedbackId);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    List<Feedback> findByIsRead(Boolean isRead);
}