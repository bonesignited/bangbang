package com.zlx.bangbang.service;

import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.dto.UserInfoDTO;
import com.zlx.bangbang.dto.UserLoginDTO;

public interface IUserService {

    User login(UserLoginDTO userLoginDTO);

    User register(String username, String password);

    UserInfoDTO updateUserInfo(UserInfoDTO userInfoDTO);

    UserInfoDTO getUserInfo(String id);

    User getUserById(String userId);
}
