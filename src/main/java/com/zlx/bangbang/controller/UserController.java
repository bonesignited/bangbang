package com.zlx.bangbang.controller;

import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.dto.UserInfoDTO;
import com.zlx.bangbang.dto.UserLoginDTO;
import com.zlx.bangbang.service.impl.UserServiceImpl;
import com.zlx.bangbang.utils.ResultUtil;
import com.zlx.bangbang.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) {
        User user = userService.login(userLoginDTO);

        return ResultUtil.success(tokenUtil.generateToken(user.getId()));
    }


    @PostMapping("/user/register")
    public ResponseEntity register(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) {
        String username = userLoginDTO.getId();
        String password = userLoginDTO.getPassword();

        User user = userService.register(username, password);
        if (user == null) {
            return ResultUtil.error("注册失败请重试");
        }
        return ResultUtil.success("注册成功", user);
    }

    @PostMapping("/user/update-info")
    public ResponseEntity updateUserInfo(@RequestBody UserInfoDTO userInfoDTO, HttpServletRequest request, HttpServletResponse response) {
        if (validateToken(request, response)) return ResultUtil.error("token 已过期");

        UserInfoDTO user = userService.updateUserInfo(userInfoDTO);
        if (user == null) {
            return ResultUtil.error("修改信息失败");
        }
        return ResultUtil.success("修改信息成功", userInfoDTO);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity fetchUserInfo(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        if (validateToken(request, response)) return ResultUtil.error("token 已过期");

        UserInfoDTO userInfoDTO = userService.getUserInfo(id);
        if (userInfoDTO == null) {
            return ResultUtil.error("获取用户信息失败");
        }
        return ResultUtil.success("获取用户信息成功", userInfoDTO);
    }

    private boolean validateToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization").substring(7);
        String validResult = tokenUtil.validateToken(token);
        if (validResult == null) {
            return true;
        }
        response.setHeader("token", validResult);
        return false;
    }
}
