package com.zlx.bangbang.controller;

import com.zlx.bangbang.constants.Constant;
import com.zlx.bangbang.exceptions.SubstituteException;
import com.zlx.bangbang.service.AddressService;
import com.zlx.bangbang.service.UserService;
import com.zlx.bangbang.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class BaseController {

    HttpServletRequest request;

    @Autowired
    UserService userService;

    @Autowired
    protected AddressService addressService;

    @Autowired
    public BaseController(HttpServletRequest request, UserService userService) {
        this.request = request;
        this.userService = userService;
    }

    public BaseController() {
    }
}
