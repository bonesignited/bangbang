package com.zlx.bangbang.controller;

import com.zlx.bangbang.constants.Constant;
import com.zlx.bangbang.domain.Address;
import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.dto.AddressDTO;
import com.zlx.bangbang.dto.ModifyUserInfoDTO;
import com.zlx.bangbang.dto.UserLoginDTO;
import com.zlx.bangbang.enums.ResultEnum;
import com.zlx.bangbang.exceptions.SubstituteException;
import com.zlx.bangbang.service.AddressService;
import com.zlx.bangbang.service.UserService;
import com.zlx.bangbang.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@Slf4j
public class UserController{
    @Autowired
    UserService userService;

    @Autowired
    protected AddressService addressService;


    @PostMapping("/login/user")
    public ResponseEntity login(@RequestBody UserLoginDTO userLoginDTO, HttpSession session) {

        User user = userService.login(userLoginDTO);
        if (user == null) {
            return ResultUtil.error(10001, "用户不存在");
        }

        session.setAttribute(Constant.CURRENT_USER, user);
        return ResultUtil.success(user);
    }


    @PostMapping("/user/register")
    public ResponseEntity register(@RequestBody UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getId();
        String password = userLoginDTO.getPassword();

        User user = userService.register(username, password);
        if (user == null) {
            return ResultUtil.error("注册失败请重试");
        }

        // 为用户自动登录
        User loggedUser = userService.login(userLoginDTO);
        return ResultUtil.success("注册成功", loggedUser);
    }

    /**
     * 修改用户信息
     */
    @PostMapping(value = "/info/{userId}")
    public ResponseEntity modifyUserInfo(@PathVariable String userId, @RequestBody ModifyUserInfoDTO modifyUserInfoDTO,
                                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error("参数不全");
        }
        userService.modifyUserInfo(userId, modifyUserInfoDTO);
        return ResultUtil.success();
    }


    @PostMapping("/{userId}")
    public ResponseEntity getUserInfo(@PathVariable String userId) {


        User user = userService.getUserInfo(userId);
        if (user == null) {
            return ResultUtil.error("获取用户信息失败");
        }
        return ResultUtil.success("获取用户信息成功", user);
    }


    /**
     * 地址相关
     */
    @PostMapping(value = "/user/address")
    public ResponseEntity addAddress(@RequestBody AddressDTO address) throws AccessDeniedException {

        String userId = address.getUserId();
        addressService.addUsualAddress(userId, address);
        return ResultUtil.success();
    }

    @GetMapping(value = "/user/address/delete")
    public ResponseEntity deleteAddress(Integer addressId, String userId) throws AccessDeniedException {
        addressService.deleteAddress(addressId, userId);
        return ResultUtil.success();
    }

    @PostMapping("/user/address/modify")
    public ResponseEntity modifyAddress(@RequestBody AddressDTO address) throws AccessDeniedException {
        addressService.modifyAddress(address.getAddressId(), address.getUserId(), address);
        return ResultUtil.success();
    }

    /**
     * 获取所有常用地址列表, 当key不为空的时候按关键词进行模糊匹配
     */
    @GetMapping(value = "/user/addresses/{userId}")
    public ResponseEntity getAllAddress(@PathVariable String userId, String key) {
        List<Address> addresses;
        if (key != null) {
            addresses = addressService.getAllByAddress(userId, key);
        } else {
            addresses = addressService.getUsualAddress(userId);
        }
        return ResultUtil.success(addresses);
    }
}
