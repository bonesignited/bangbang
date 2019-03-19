package com.zlx.bangbang.service;

import com.zlx.bangbang.domain.CouponRecord;

import java.util.List;

public interface CouponRecordService {
    /**
     * 通过用户 id 和优惠券 id，查询某用户是否有领取该优惠券的记录
     * 若有，返回该优惠券领取的信息
     */
    CouponRecord findByOwnerAndCouponId(String ownerId, Integer couponId);

    /**
     * 更新优惠券领取信息
     */
    void update(CouponRecord couponRecord);

    /**
     * 添加优惠券领取信息
     */
    void create(String userId, Integer couponId);

    /**
     * 获取所有该用户领取的未过期的优惠券
     */
    List<CouponRecord> findLiveByUserId(String userId);

    /**
     * 获取所有该用户领取的所有优惠券
     */
    List<CouponRecord> findGetByUserId(String userId);
}
