package com.zlx.bangbang.handler;

import com.zlx.bangbang.exceptions.SubstituteException;
import com.zlx.bangbang.utils.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常捕获类
 */
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(SubstituteException.class)
    @ResponseBody
    public ResponseEntity handleSubstituteException(SubstituteException e) {
        return ResultUtil.error(e.getCode(), e.getMessage());
    }
}
