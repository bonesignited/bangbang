package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.config.AdminCongiurableConfig;
import com.zlx.bangbang.dao.IndentMapper;
import com.zlx.bangbang.dao.UserMapper;
import com.zlx.bangbang.domain.*;
import com.zlx.bangbang.enums.GenderEnum;
import com.zlx.bangbang.enums.IndentStateEnum;
import com.zlx.bangbang.enums.IndentTypeEnum;
import com.zlx.bangbang.exceptions.CheckException;
import com.zlx.bangbang.exceptions.SubtituteException;
import com.zlx.bangbang.service.*;
import com.zlx.bangbang.vo.IndentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class IndentServiceImpl implements IndentService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IndentMapper indentMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CouponRecordService couponRecordService;

    @Autowired
    private CouponInfoService couponInfoService;


    /**
     * note 每次用 dao 层的组件获取信息后都要判空
     */

    /**
     * 将 indent 封装为 indentVO
     * @param indent
     * @return
     */
    private IndentVO indent2VO(Indent indent) throws CheckException {
        IndentVO indentVO = new IndentVO();
        BeanUtils.copyProperties(indent, indentVO);
        Address shipping = addressService.getById(indent.getShippingAddressId());
        if (indent.getIndentType() != IndentTypeEnum.HELP_OTHER) {
            if (shipping == null || !shipping.getUserId().equals(indent.getPublisherId())) {
                log.error("[获取订单信息] 送达地址信息有误, indent = {}, shipping = {}", indent, shipping);
                throw new CheckException("订单信息有误，送达地址信息有误");
            } else {
                indentVO.setShippingAddress(shipping.getAddress());
                indentVO.setPublisherName(shipping.getUserName());
                indentVO.setPublisherPhone(shipping.getPhone());
            }
        }

        // 获取下单用户，并通过用户获取学校信息
        User publisher = userService.getUserById(indent.getPublisherId());
        if (publisher == null) {
            log.error("[获取订单信息] publisherId 不存在，indent={}", indent);
            throw new CheckException("订单信息有误，下单用户不存在");
        }
        School publisherSchool = schoolService.getById(publisher.getSchoolId());
        if (publisherSchool == null) {
            log.error("[获取订单信息] publisherSchoolId 不存在，indent={},\npublisher={}", indent, publisher);
            throw new CheckException("下单用户信息有误，请完善学校信息");
        } else {
            indentVO.setPublisherSchoolId(publisherSchool.getId());
            indentVO.setPublisherSchool(publisherSchool.getSchoolName());
        }
        indentVO.setPublisherGender(publisher.getGender());
        indentVO.setPublisherAvatar(publisher.getAvatar());
        indentVO.setPublisherNickName(publisher.getUserName());

        // 检验该订单是否已被接，若被接，拼接上接单人信息
        if (indent.getPerformerId() != null) {
            User performer = userService.getUserById(indent.getPerformerId());
            if (performer == null) {
                log.error("[获取订单信息] performerId 不存在，indent={}");
                throw new CheckException("订单信息有误，接单人不存在");
            }

            School performerSchool = schoolService.getById(performer.getSchoolId());
            if (performerSchool == null) {
                log.error("[获取订单信息] performerId 不存在，indent={}，\nperformer={}", indent, performer);
                throw new CheckException("接单用户信息有误，请完善学校信息");
            } else {
                indentVO.setPerformerSchoolId(performerSchool.getId());
                indentVO.setPerformerSchool(performerSchool.getSchoolName());
            }
            indentVO.setPerformerGender(performer.getGender());
            indentVO.setPerformerAvatar(performer.getAvatar());
            indentVO.setPerformerNickName(performer.getUserName());
            indentVO.setPerformerPhone(performer.getPhone());
        }

        return indentVO;
    }

    private List<IndentVO> indent2VO(List<Indent> indents) {
        List<IndentVO> indentVOS = new ArrayList<>();
        indents.forEach(e -> {
            try {
                indentVOS.add(indent2VO(e));
            } catch (CheckException ignored) {
                // note 捕获了但不想处理的异常命名时可用 ignored
            }
        });
        return indentVOS;
    }

    private void checkUserInfo(User user) {
        if (user.getSchoolId() == null || user.getTrueName() == null || user.getPhone() == null) {
            log.error("用户信息不全");
            throw new SubtituteException("用户信息不全");
        }
    }

    @Override
    public Integer create(Indent indent) {
        // 1. 验证参数是否有效
        // 1.1 验证订单金额
        if (indent.getIndentPrice() < AdminCongiurableConfig.leastPrice) {
            log.error("[创建订单] 创建失败，订单金额不能小于 3 元，indent={}", indent);
            throw new SubtituteException("订单金额不能小于 3 元");
        }

        // 1.1 验证下单的用户 id 是否存在
        User user = userService.getUserById(indent.getPublisherId());
        if (user == null) {
            log.error("[创建订单] 创建失败，下单用户不存在，publisherId={}, indent={}", indent.getPublisherId(), indent);
            throw new SubtituteException("下单用户不存在，publisherId 有误");
        }
        checkUserInfo(user);

        // 1.2 若不是随意帮，检验用户填入的 shippingId 是否存在，是否属于该用户
        if (!indent.getIndentType().equals(IndentTypeEnum.HELP_OTHER)) {
            Address shipping = addressService.getById(indent.getShippingAddressId());
            if (shipping == null || !shipping.getUserId().equals(indent.getPublisherId())) {
                log.error("[创建订单] 创建失败，送达地址信息有误，indent={}，shipping={}", indent, shipping);
                throw new SubtituteException("订单信息有误，送达地址信息有误");
            }
        }

        // 1.3 处理优惠券
        if (indent.getCouponId() != null) {
            CouponRecord record = couponRecordService.findByOwnerAndCouponId(indent.getPublisherId(), indent.getCouponId());
            if (record == null || record.getIsUsed() || record.getInvalidTime().before(new Date())) {
                log.error("[创建订单] 创建失败，优惠券失效，indent={}，record={}", indent, record);
                throw new SubtituteException("订单信息误，优惠券信息有误");
            }

            CouponInfo couponInfo = couponInfoService.findById(record.getCouponId());
            if (indent.getIndentPrice() < couponInfo.getLeastPrice()) {
                log.error("[创建订单] 创建失败，优惠券不可用，indent={}，record={}", indent, record);
                throw new SubtituteException("订单信息误，达不到优惠券满减金额");
            }

            indent.setCouponPrice(couponInfo.getReducePrice());
            //将优惠券领取信息设置为已使用
            record.setIsUsed(true);
            couponRecordService.update(record);
        }

        // 1.4 验证用户余额是否足够支付，若足够，扣钱
        int totalPrice = indent.getIndentPrice() - indent.getCouponPrice();
        BigDecimal userBalance = user.getBalance();
        if (userBalance.compareTo(BigDecimal.valueOf(totalPrice)) < 0) {
            // 若用户余额不足以支付订单
            log.error("[创建订单] 创建失败，用户余额不足，userBalance={}，indentPrice={}", userBalance, totalPrice);
            throw new SubtituteException("用户余额不足");
        }

        // 扣钱
        user.setBalance(userBalance.subtract(BigDecimal.valueOf(totalPrice)));
        userMapper.updateByPrimaryKeySelective(user);
        indent.setTotalPrice(totalPrice);
        // 设置订单状态
        indent.setIndentState(IndentStateEnum.WAIT_FOR_PERFORMER);
        // 将订单保存到数据库中
        indentMapper.insert(indent);
        return indent.getIndentId();
    }

    @Override
    public void addIndentPrice(Integer indentId, String userId) {

    }

    @Override
    public void takeIndent(Integer indentId, String userId) {

    }

    @Override
    public void arrivedIndent(Integer indentId, String userId) {

    }

    @Override
    public BigDecimal finishedIndent(Integer indentId, String userId) {
        return null;
    }

    @Override
    public void canceledIndent(Integer indentId, String userId) {

    }

    @Override
    public List<IndentVO> getUserPublishedIndent(String userId) {
        return null;
    }

    @Override
    public List<IndentVO> getUserPerformedIndent(String userId) {
        return null;
    }

    @Override
    public List<IndentVO> getWait(Integer sortType, GenderEnum sexType) {
        return null;
    }

    @Override
    public IndentVO getIndentDetail(Integer indentId, String userId) {
        return null;
    }
}
