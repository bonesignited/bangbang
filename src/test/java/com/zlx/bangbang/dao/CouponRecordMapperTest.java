package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.CouponRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponRecordMapperTest {
    @Autowired
    private CouponRecordMapper couponRecordMapper;

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
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void findByCouponIdAndOwnerId() {
        CouponRecord couponRecord = couponRecordMapper.findByCouponIdAndOwnerId(31, "pJXIOQ");
        System.out.println(couponRecord.toString());
    }

    @Test
    public void findLiveByUserId() {
        List<CouponRecord> records = couponRecordMapper.findLiveByUserId("pjXIOQ");
        for (CouponRecord record : records) {
            System.out.println(record);
        }
    }

    @Test
    public void findGetByUserId() {
    }
}