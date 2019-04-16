package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.CouponInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponInfoMapperTest {
    @Autowired
    private CouponInfoMapper couponInfoMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        CouponInfo record = couponInfoMapper.selectByPrimaryKey(40);
        System.out.println(record.getInvalidTime());
    }

    @Test
    public void updateByPrimaryKeySelective() {
        CouponInfo record = new CouponInfo();
        record.setCouponId(40);
        GregorianCalendar calendar = new GregorianCalendar(2019, 5, 1);
        record.setInvalidTime(calendar.getTime());
        int rows = couponInfoMapper.updateByPrimaryKeySelective(record);
        System.out.println(rows);
    }

    @Test
    public void updateByPrimaryKeyWithBLOBs() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void findAllGet() {
    }

    @Test
    public void findAllGetExcept() {
    }

    @Test
    public void findTop5ByInvalidTimeAfterAndIsFalseOrderByCouponIdDesc() {
    }

    @Test
    public void findLiveCouponListByUserId(){
        List<CouponInfo> couponInfoList = couponInfoMapper.findLiveCouponListByUserId("A12345");
        for (CouponInfo couponInfo : couponInfoList) {
            System.out.println(couponInfo.getCouponId());
        }
    }

    @Test
    public void findGetCouponListByUserId() {
        List<CouponInfo> couponInfoList = couponInfoMapper.findGetCouponListByUserId("A12345");
        for (CouponInfo couponInfo : couponInfoList) {
            System.out.println(couponInfo.getCouponId());
        }
    }
}