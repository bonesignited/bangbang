package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.dao.CouponInfoMapper;
import com.zlx.bangbang.domain.CouponInfo;
import com.zlx.bangbang.domain.CouponRecord;
import com.zlx.bangbang.dto.UserCouponDTO;
import com.zlx.bangbang.service.CouponInfoService;
import com.zlx.bangbang.service.CouponRecordService;
import com.zlx.bangbang.vo.CouponListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CouponInfoServiceImpl implements CouponInfoService {
    @Autowired
    CouponInfoMapper couponInfoMapper;

    @Autowired
    CouponRecordService couponRecordService;

    @Override
    public CouponInfo findById(Integer couponId) {
        return couponInfoMapper.selectByPrimaryKey(couponId);
    }

    @Override
    public CouponListVO findListByUserId(String userId) {
        CouponListVO couponListVO = new CouponListVO();
        // 1 获取已领取且可用的优惠券
        // 1.1 获取所有已领取信息
        List<CouponRecord> couponRecords = couponRecordService.findLiveByUserId(userId);
        List<Integer> couponIds = new ArrayList<>();
        couponRecords.forEach(x -> {
            if (!x.getIsUsed()) {
                couponIds.add(x.getCouponId());
            }
        });
        List<CouponInfo> liveCouponInfos = couponInfoMapper.findAllByCouponIdIsInAndIsDeletedIsFalse(couponIds);
        couponListVO.setLiveCoupons(liveCouponInfos);
        // 2 获取未领取但可领取的优惠券
        // 获取该用户已领取的优惠券 id
        List<Integer> couponGetIds = couponRecords.stream().map(CouponRecord::getCouponId).collect(Collectors.toList());
        // 获取该用户所有可领取的优惠券
        List<CouponInfo> couponInfos;
        if (couponGetIds.size() == 0) {
            couponInfos = couponInfoMapper.findAllGet();
        } else {
            couponInfos = couponInfoMapper.findAllGetExcept(couponGetIds);
        }

        couponListVO.setGetCoupons(couponInfos);
        return couponListVO;
    }

    @Override
    public CouponInfo getSpecificCoupon(Integer id) {
        return couponInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserCouponDTO> getRecentFiveCouponInfo() {
        List<CouponInfo> couponInfos = couponInfoMapper.
                findTop5ByInvalidTimeAfterAndIsFalseOrderByCouponIdDesc(new Date());
        log.info("coupon size: " + couponInfos.size());
        List<UserCouponDTO> userCouponDTOS = new ArrayList<>();
        couponInfos.forEach(x -> {
            UserCouponDTO userCouponDTO = new UserCouponDTO();
            BeanUtils.copyProperties(x, userCouponDTO);
            if (x.getPicture() != null) {
                userCouponDTO.setPictureLink("");
            }
            userCouponDTOS.add(userCouponDTO);
        });
        return userCouponDTOS;
    }

    public CouponInfo findByCouponId(Integer couponId) {
        return null;
    }
}
