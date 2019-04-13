package com.zlx.bangbang.service;

import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.dto.ModifyUserInfoDTO;
import com.zlx.bangbang.dto.UserInfoDTO;
import com.zlx.bangbang.dto.UserLoginDTO;

import java.math.BigDecimal;

public interface UserService {

    UserInfoDTO login(UserLoginDTO userLoginDTO);

    UserInfoDTO register(String username, String password);

    boolean modifyUserInfo(String userId, ModifyUserInfoDTO newInfo);

    UserInfoDTO getUserInfo(String id);

    User getUserById(String userId);

    void saveUser(User user);

    void reduceBalance(String userId, BigDecimal number);
}
