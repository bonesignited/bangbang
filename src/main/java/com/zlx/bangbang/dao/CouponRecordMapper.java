package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.CouponRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CouponRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CouponRecord record);

    int insertSelective(CouponRecord record);

    CouponRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponRecord record);

    int updateByPrimaryKey(CouponRecord record);

    CouponRecord findByCouponIdAndOwnerId(@Param("couponId") Integer couponId,@Param("ownerId") String ownerId);

    List<CouponRecord> findLiveByUserId(String userId);

    List<CouponRecord> findGetByUserId(String userId);
}