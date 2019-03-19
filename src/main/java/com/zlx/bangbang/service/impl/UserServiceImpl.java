package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.dao.UserMapper;
import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.dto.UserInfoDTO;
import com.zlx.bangbang.dto.UserLoginDTO;
import com.zlx.bangbang.service.IUserService;
import com.zlx.bangbang.utils.RandomUtil;
import com.zlx.bangbang.utils.ResultUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;


    private boolean matches(String rawPassword, String password) {
        if (rawPassword == null) {
            return false;
        }
        return password.equals(DigestUtils.md5Hex(rawPassword));
    }

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        User user = userMapper.selectByPrimaryKey(userLoginDTO.getId());

        String password = user.getUserPass();
        // 检查密码是否相等
        if (matches(userLoginDTO.getPassword(), password)) {
            return user;
        }
        return user;
    }

    @Override
    public User register(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setUserPass(password);

        // 设置 id，客户端发请求需要带上它
        user.setId(RandomUtil.generateUserId());

        user.setBalance(new BigDecimal(0));
        user.setAllIncome(new BigDecimal(0));
        user.setCreateTime(new Date(System.currentTimeMillis()));

        int rows = userMapper.insertSelective(user);

        if (rows == 0) {
            return null;
        }
        return user;
    }

    @Override
    public UserInfoDTO updateUserInfo(UserInfoDTO userInfoDTO) {

        // 若为第一次提交信息
        User databaseUserInfo = userMapper.selectByPrimaryKey(userInfoDTO.getId());
        if (databaseUserInfo.getTrueName() == null) {
            BeanUtils.copyProperties(databaseUserInfo, userInfoDTO);
            int rows = userMapper.updateByPrimaryKeySelective(databaseUserInfo);
            if (rows != 1) {
                return null;
            }
            return userInfoDTO;
        }

        // 之前已提交过信息，现在要修改
        // 性别和姓名不能改
        databaseUserInfo.setAvatar(userInfoDTO.getAvatar());
        databaseUserInfo.setPhone(userInfoDTO.getPhone());
        databaseUserInfo.setSchoolId(userInfoDTO.getSchoolId());

        // 设置此次更新时间
        databaseUserInfo.setUpdateTime(new Date(System.currentTimeMillis()));

        int rows = userMapper.updateByPrimaryKeySelective(databaseUserInfo);
        if (rows != 1) {
            return null;
        }

        return userInfoDTO;
    }

    @Override
    public UserInfoDTO getUserInfo(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(user, userInfoDTO);

        return userInfoDTO;
    }

    @Override
    public User getUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
