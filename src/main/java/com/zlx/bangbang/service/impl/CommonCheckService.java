package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.domain.CouponInfo;
import com.zlx.bangbang.domain.User;
import com.zlx.bangbang.enums.ResultEnum;
import com.zlx.bangbang.exceptions.SubtituteException;
import com.zlx.bangbang.service.CouponInfoService;
import com.zlx.bangbang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CommonCheckService {
    @Autowired
    UserService userService;

    @Autowired
    CouponInfoService couponInfoService;

    public User checkUserByUserId(String userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            log.error("用户不存在，userId = {}", userId);
            throw new SubtituteException(ResultEnum.USER_NOT_EXISTS);
        }
        return user;
    }

    public CouponInfo checkCouponInfoById(Integer couponId) {
        CouponInfo couponInfo = couponInfoService.findById(couponId);
        if (couponInfo == null) {
            log.error("优惠券不存在，couponId = {}", couponId);
            throw new SubtituteException(ResultEnum.COUPON_NOT_EXISTS);
        }
        return couponInfo;
    }
}
