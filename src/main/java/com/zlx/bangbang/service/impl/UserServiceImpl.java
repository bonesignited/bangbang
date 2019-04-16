package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.dao.SchoolMapper;
import com.zlx.bangbang.dao.UserMapper;
import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.dto.ModifyUserInfoDTO;
import com.zlx.bangbang.dto.UserInfoDTO;
import com.zlx.bangbang.dto.UserLoginDTO;
import com.zlx.bangbang.enums.GenderEnum;
import com.zlx.bangbang.enums.ResultEnum;
import com.zlx.bangbang.exceptions.SubstituteException;
import com.zlx.bangbang.service.UserService;
import com.zlx.bangbang.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SchoolMapper schoolMapper;


    private boolean matches(String rawPassword, String password) {
        if (rawPassword == null) {
            return false;
        }
        return password.equals(DigestUtils.md5Hex(rawPassword));
    }

    @Override
    public UserInfoDTO login(UserLoginDTO userLoginDTO) {
        User user = userMapper.selectByPrimaryKey(userLoginDTO.getId());
        if (user == null) {
            return null;
        }
        String password = user.getUserPass();
        // 检查密码是否相等
        if (!matches(userLoginDTO.getPassword(), password)) {
            throw new SubstituteException(ResultEnum.PWD_ERROR);
        }

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setSchool(schoolMapper.findSchoolById(user.getSchoolId()).getSchoolName());
        BeanUtils.copyProperties(user, userInfoDTO);
        return userInfoDTO;
    }

    @Override
    public UserInfoDTO register(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setUserPass(password);

        user.setId(RandomUtil.generateUserId());
        user.setAvatar("http://www.sweethaochen.cn/icon/images/my.jpg");
        user.setGender(GenderEnum.NO_LIMITED);
        user.setBalance(new BigDecimal(0));
        user.setAllIncome(new BigDecimal(0));
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setUpdateTime(new Date(System.currentTimeMillis()));

        int rows = userMapper.insertSelective(user);

        if (rows == 0) {
            return null;
        }

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setSchool(null);
        BeanUtils.copyProperties(user, userInfoDTO);

        return userInfoDTO;
    }

    @Override
    public boolean modifyUserInfo(String userId, ModifyUserInfoDTO newInfo) {
        User databaseUserInfo = userMapper.selectByPrimaryKey(userId);
        Integer schoolId = schoolMapper.findSchoolByName(newInfo.getSchoolName());
        if (schoolId == null) {
            throw new SubstituteException(ResultEnum.SCHOOL_ERROR);
        }
        databaseUserInfo.setSchoolId(schoolId);
        BeanUtils.copyProperties(newInfo, databaseUserInfo);
        int rows = userMapper.updateByPrimaryKeySelective(databaseUserInfo);
        return rows == 1;
    }

    @Override
    public UserInfoDTO getUserInfo(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(user, userInfoDTO);
        userInfoDTO.setSchool(schoolMapper.findSchoolById(user.getSchoolId()).getSchoolName());
        return userInfoDTO;
    }

    @Override
    public User getUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void saveUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void reduceBalance(String userId, BigDecimal number) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            log.error("【扣除用户余额】扣除失败，不存在该用户，userId={}", userId);
            throw new SubstituteException("【扣除用户余额】扣除失败，不存在该用户");
        }
        user.setBalance(user.getBalance().subtract(number));

        userMapper.updateByPrimaryKeySelective(user);
    }
}
