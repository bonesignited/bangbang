package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.dao.CouponRecordMapper;
import com.zlx.bangbang.domain.CouponInfo;
import com.zlx.bangbang.domain.CouponRecord;
import com.zlx.bangbang.exceptions.SubtituteException;
import com.zlx.bangbang.service.impl.CommonCheckService;
import com.zlx.bangbang.service.CouponRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CouponRecordServiceImpl implements CouponRecordService {
    // todo 要做分页处理


    @Autowired
    CouponRecordMapper couponRecordMapper;

    @Autowired
    CommonCheckService commonCheckService;

    @Override
    public CouponRecord findByOwnerAndCouponId(String ownerId, Integer couponId) {
        return couponRecordMapper.findByCouponIdAndOwnerId(couponId, ownerId);
    }

    @Override
    public void update(CouponRecord couponRecord) {
        couponRecordMapper.updateByPrimaryKeySelective(couponRecord);
    }

    @Override
    public void create(String userId, Integer couponId) {
        CouponRecord couponRecord = couponRecordMapper.findByCouponIdAndOwnerId(couponId, userId);
        if (couponRecord == null) {
            log.error("已领取过该优惠券，userId = {}，couponId = {}", userId, couponId);
            throw new SubtituteException("已领取过该优惠券");
        }

        // 校验用户是否存在
        commonCheckService.checkUserByUserId(userId);

        // 获取优惠券信息，从而获取失效时间
        CouponInfo couponInfo = commonCheckService.checkCouponInfoById(couponId);

        // 设置 couponRecord
        couponRecord.setCouponId(couponId);
        couponRecord.setOwnerId(userId);
        couponRecord.setInvalidTime(couponInfo.getInvalidTime());
        couponRecordMapper.insert(couponRecord);
    }

    @Override
    public List<CouponRecord> findLiveByUserId(String userId) {
        commonCheckService.checkUserByUserId(userId);
        return couponRecordMapper.findLiveByUserId(userId);
    }

    @Override
    public List<CouponRecord> findGetByUserId(String userId) {
        commonCheckService.checkUserByUserId(userId);
        return couponRecordMapper.findGetByUserId(userId);
    }
}
