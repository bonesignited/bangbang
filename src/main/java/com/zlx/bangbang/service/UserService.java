package com.zlx.bangbang.service;

import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.dto.ModifyUserInfoDTO;
import com.zlx.bangbang.dto.UserLoginDTO;

import java.math.BigDecimal;

public interface UserService {

    User login(UserLoginDTO userLoginDTO);

    User register(String username, String password);

    boolean modifyUserInfo(String userId, ModifyUserInfoDTO newInfo);

    User getUserInfo(String id);

    User getUserById(String userId);

    void saveUser(User user);

    void reduceBalance(String userId, BigDecimal number);
}
