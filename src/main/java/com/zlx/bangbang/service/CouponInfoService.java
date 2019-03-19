package com.zlx.bangbang.service;

import com.zlx.bangbang.domain.CouponInfo;
import com.zlx.bangbang.dto.UserCouponDTO;
import com.zlx.bangbang.vo.CouponListVO;

import java.util.List;

public interface CouponInfoService {

    /**
     * 通过 id 获得优惠券信息
     */
    CouponInfo findById(Integer couponId);

    /**
     * 获取某用户所有已领取未过期 / 可领取的优惠券
     */
    CouponListVO findListByUserId(String userId);

    CouponInfo getSpecificCoupon(int id);

    /**
     * 返回首页轮播图中的优惠券信息
     */
    List<UserCouponDTO> getRecentFiveCouponInfo();

//    /**
//     * 通过优惠券 id 获取某优惠券信息
//     */
//    CouponInfo findByCouponId(Integer couponId);
}
