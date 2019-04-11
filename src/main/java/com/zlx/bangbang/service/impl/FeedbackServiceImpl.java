package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.dao.FeedbackMapper;
import com.zlx.bangbang.domain.Feedback;
import com.zlx.bangbang.service.FeedbackService;
import com.zlx.bangbang.service.impl.CommonCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    CommonCheckService commonCheckService;

    @Override
    public void create(Feedback feedback) {
        commonCheckService.checkUserByUserId(feedback.getUserId());
        feedbackMapper.insertSelective(feedback);
    }
}