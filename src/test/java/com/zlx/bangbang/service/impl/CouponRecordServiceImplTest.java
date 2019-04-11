package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.dao.CouponRecordMapper;
import com.zlx.bangbang.domain.CouponRecord;
import com.zlx.bangbang.service.CouponRecordService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponRecordServiceImplTest {

    @Autowired
    private CouponRecordService couponRecordService;

    @Test
    public void findByOwnerAndCouponId() {
        String userId = "A12345";
        Integer couponId = 43;
        CouponRecord couponRecord = couponRecordService.findByOwnerAndCouponId(userId, couponId);
        Assert.assertEquals(Integer.valueOf(180), couponRecord.getId());
    }

    @Test
    public void update() {
        CouponRecord couponRecord = new CouponRecord();
        couponRecord.setIsUsed(true);
        couponRecord.setId(180);
        couponRecordService.update(couponRecord);
    }

    @Test
    public void create() {
        String userId = "B12345";
        Integer couponId = 42;
        couponRecordService.create(userId, couponId);
    }

    @Test
    public void findLiveByUserId() {
        String userId = "A12345";
        List<CouponRecord> couponRecordList = couponRecordService.findLiveByUserId(userId);
        Assert.assertEquals(Integer.valueOf(43), couponRecordList.get(0).getCouponId());
    }

    @Test
    public void findGetByUserId() {
        String userId = "A12345";
        List<CouponRecord> couponRecordList = couponRecordService.findGetByUserId(userId);
        Assert.assertEquals(Integer.valueOf(39), couponRecordList.get(1).getCouponId());
    }
}