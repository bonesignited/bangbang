package com.zlx.bangbang.controller;

import com.zlx.bangbang.domain.Feedback;
import com.zlx.bangbang.enums.ResultEnum;
import com.zlx.bangbang.exceptions.SubstituteException;
import com.zlx.bangbang.service.FeedbackService;
import com.zlx.bangbang.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
@Slf4j
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity create(@RequestBody Feedback feedback, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldError() == null ? ResultEnum.PARAM_ERROR.getMsg()
                    : bindingResult.getFieldError().getDefaultMessage();
            log.error("[创建反馈]创建失败，feedback={}", feedback);
            throw new SubstituteException(msg, ResultEnum.PARAM_ERROR.getCode());
        }
        feedback.setIsRead(false);
        feedbackService.create(feedback);
        return ResultUtil.success();
    }
}
