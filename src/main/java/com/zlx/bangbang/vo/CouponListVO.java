package com.zlx.bangbang.vo;

import com.zlx.bangbang.domain.CouponInfo;
import lombok.Data;

import java.util.List;

@Data
public class CouponListVO {
    /**
     * 可用优惠券列表
     */
    private List<CouponInfo> liveCoupons;

    /**
     * 可领取优惠券列表
     */
    private List<CouponInfo> getCoupons;
}
