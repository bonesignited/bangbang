package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.CouponInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CouponInfoMapper {
    // todo 测试

    int deleteByPrimaryKey(Integer couponId);

    int insert(CouponInfo record);

    int insertSelective(CouponInfo record);

    CouponInfo selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(CouponInfo record);

    int updateByPrimaryKeyWithBLOBs(CouponInfo record);

    int updateByPrimaryKey(CouponInfo record);

    List<CouponInfo> findAllGet();

    List<CouponInfo> findAllGetExcept(List<Integer> couponGetIds);

    List<CouponInfo> findTop5ByInvalidTimeAfterAndIsFalseOrderByCouponIdDesc(Date date);
}