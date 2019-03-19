package com.zlx.bangbang.controller;


import com.sun.deploy.net.HttpResponse;
import com.zlx.bangbang.form.CouponUserForm;
import com.zlx.bangbang.service.CouponInfoService;
import com.zlx.bangbang.service.CouponRecordService;
import com.zlx.bangbang.utils.ResultUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.nio.ch.IOUtil;
import sun.rmi.server.InactiveGroupException;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    CouponInfoService couponInfoService;

    @Autowired
    CouponRecordService couponRecordService;


    /**
     * 获取某用户未过期且未领取的优惠券列表
     */
    @GetMapping("/list/{userId}")
    public ResponseEntity findLiveByUserId(@PathVariable String userId) {
        return ResultUtil.success(couponInfoService.findListByUserId(userId));
    }

    /**
     * 通过 couponId 获取优惠券信息
     */
    @GetMapping("/info/{couponId}")
    public ResponseEntity getOneByCouponId(@PathVariable Integer couponId) {
        return ResultUtil.success(couponInfoService.findById(couponId));
    }

    /**
     * 某用户领取优惠券
     */
    public ResponseEntity getCoupon(@RequestBody CouponUserForm couponUserForm) {
        couponRecordService.create(couponUserForm.getUserId(), couponUserForm.getCouponId());
        return ResultUtil.success();
    }

    @GetMapping("/img/{id}")
    public void getImg(@PathVariable int id, HttpServletResponse response) throws IOException {
        IOUtils.copy(new ByteArrayInputStream(couponInfoService.getSpecificCoupon(id).getPicture()),
                response.getOutputStream());
    }

    /**
     * 获取最近 5 个优惠券
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity getCouponRecentList() {
        return ResultUtil.success(couponInfoService.getRecentFiveCouponInfo());
    }
}
