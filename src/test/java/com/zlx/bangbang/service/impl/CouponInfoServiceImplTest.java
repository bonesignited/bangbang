package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.domain.CouponInfo;
import com.zlx.bangbang.service.CouponInfoService;
import com.zlx.bangbang.vo.CouponListVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponInfoServiceImplTest {

    @Autowired
    private CouponInfoService couponInfoService;

    @Test
    public void findById() {
        Integer couponId = 40;
        CouponInfo couponInfo = couponInfoService.findById(couponId);
        System.out.println(couponInfo.getValidTime());
    }

    @Test
    public void findListByUserId() {
        String userId = "A12345";
        CouponListVO couponListVO = couponInfoService.findListByUserId(userId);
        for (CouponInfo getCouponInfo : couponListVO.getGetCoupons()) {
            System.out.println(getCouponInfo.getCouponId());
        }
        System.out.println("-----------");
        for (CouponInfo liveCouponInfo : couponListVO.getLiveCoupons()) {
            System.out.println(liveCouponInfo.getCouponId());
        }
    }

    @Test
    public void getSpecificCoupon() {
    }

    @Test
    public void getRecentFiveCouponInfo() {

    }
}