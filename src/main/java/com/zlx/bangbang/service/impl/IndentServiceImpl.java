package com.zlx.bangbang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zlx.bangbang.config.AdminCongiurableConfig;
import com.zlx.bangbang.dao.IndentMapper;
import com.zlx.bangbang.dao.UserMapper;
import com.zlx.bangbang.domain.*;
import com.zlx.bangbang.enums.*;
import com.zlx.bangbang.exceptions.CheckException;
import com.zlx.bangbang.exceptions.SubstituteException;
import com.zlx.bangbang.service.*;
import com.zlx.bangbang.utils.CommonUtil;
import com.zlx.bangbang.vo.IndentListVO;
import com.zlx.bangbang.vo.IndentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(rollbackFor = Exception.class)
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
     * 将 indent 封装为 indentVO
     *
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
            throw new SubstituteException("用户信息不全");
        }
    }

    @Override
    public Integer create(Indent indent) {
        // 1. 验证参数是否有效
        // 1.1 验证订单金额
        if (indent.getIndentPrice() < AdminCongiurableConfig.leastPrice) {
            log.error("[创建订单] 创建失败，订单金额不能小于 3 元，indent={}", indent);
            throw new SubstituteException("订单金额不能小于 3 元");
        }

        // 1.1 验证下单的用户 id 是否存在
        User user = userService.getUserById(indent.getPublisherId());
        if (user == null) {
            log.error("[创建订单] 创建失败，下单用户不存在，publisherId={}, indent={}", indent.getPublisherId(), indent);
            throw new SubstituteException("下单用户不存在，publisherId 有误");
        }
        checkUserInfo(user);

        // 1.2 若不是随意帮，检验用户填入的 shippingId 是否存在，是否属于该用户
        if (!indent.getIndentType().equals(IndentTypeEnum.HELP_OTHER)) {
            Address shipping = addressService.getById(indent.getShippingAddressId());
            if (shipping == null || !shipping.getUserId().equals(indent.getPublisherId())) {
                log.error("[创建订单] 创建失败，送达地址信息有误，indent={}，shipping={}", indent, shipping);
                throw new SubstituteException("订单信息有误，送达地址信息有误");
            }
        }

        int totalPrice = indent.getIndentPrice();

        // 1.3 处理优惠券
        if (indent.getCouponId() != null) {
            CouponRecord record = couponRecordService.findByOwnerAndCouponId(indent.getPublisherId(), indent.getCouponId());
            if (record == null || record.getIsUsed() || record.getInvalidTime().before(new Date())) {
                log.error("[创建订单] 创建失败，优惠券失效，indent={}，record={}", indent, record);
                throw new SubstituteException("订单信息误，优惠券信息有误");
            }

            CouponInfo couponInfo = couponInfoService.findById(record.getCouponId());
            if (indent.getIndentPrice() < couponInfo.getLeastPrice()) {
                log.error("[创建订单] 创建失败，优惠券不可用，indent={}，record={}", indent, record);
                throw new SubstituteException("订单信息误，达不到优惠券满减金额");
            }

            indent.setCouponPrice(couponInfo.getReducePrice());
            //将优惠券领取信息设置为已使用
            record.setIsUsed(true);
            couponRecordService.update(record);
            totalPrice -= indent.getCouponPrice();
        }

        // 1.4 验证用户余额是否足够支付，若足够，扣钱
        BigDecimal userBalance = user.getBalance();
        if (userBalance.compareTo(BigDecimal.valueOf(totalPrice)) < 0) {
            // 若用户余额不足以支付订单
            log.error("[创建订单] 创建失败，用户余额不足，userBalance={}，indentPrice={}", userBalance, totalPrice);
            throw new SubstituteException("用户余额不足");
        }

        // 扣钱
        user.setBalance(userBalance.subtract(BigDecimal.valueOf(totalPrice)));
        int rows = userMapper.updateByPrimaryKeySelective(user);
        indent.setTotalPrice(totalPrice);
        // 设置订单状态
        indent.setIndentState(IndentStateEnum.WAIT_FOR_PERFORMER);
        // 将订单保存到数据库中
        indentMapper.insertSelective(indent);
        return indent.getIndentId();
    }

    @Override
    public void addIndentPrice(Integer indentId, String userId) {
        Indent indent = indentMapper.findByIndentId(indentId);

        if (indent == null || indent.getIndentState().equals(IndentStateEnum.COMPLETED) ||
                indent.getIndentState().equals(IndentStateEnum.CANCELED)) {
            log.error("【增加赏金】增加赏金失败，订单状态有误，indentId={}", indentId);
            throw new SubstituteException(ResultEnum.INDENT_STATE_ERROR);
        }

        if (!indent.getPublisherId().equals(userId)) {
            // 若不是下单人请求的接口
            log.error("【增加赏金】增加赏金失败，操作用户非下单人，userId={}，publisherId={}", userId, indentId);
            throw new SubstituteException("无权限，该用户非下单人");
        }

        // 查询用户余额是否足够
        User user = userService.getUserById(userId);
        if (user.getBalance().compareTo(new BigDecimal(1)) < 0) {
            log.error("【增加赏金】增加失败，余额不足，userId={}，balance={}", userId, user.getBalance());
            throw new SubstituteException("增加失败，余额不足");
        }

        // 扣钱
        userService.reduceBalance(userId, new BigDecimal(1));

        // 增加订单悬赏金
        indent.setIndentPrice(indent.getIndentPrice() + 1);
        indent.setTotalPrice(indent.getTotalPrice() + 1);
        indentMapper.updateByPrimaryKeySelective(indent);
    }

    @Override
    public void takeIndent(Integer indentId, String userId) {
        Indent indent = indentMapper.findByIndentId(indentId);
        if (indent == null || indent.getPerformerId() != null || indent.getIndentState() != IndentStateEnum.WAIT_FOR_PERFORMER) {
            log.error("【接单】接单失败，该订单状态有误，indent={}", indent);
            throw new SubstituteException(ResultEnum.INDENT_STATE_ERROR);
        }

        if (indent.getPublisherId().equals(userId)) {
            log.error("【接单】接单失败，不能接自己的单，indent={}，userId={}", indent, userId);
            throw new SubstituteException("不能接自己的单");
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            log.error("【接单】接单失败，用户不存在，userId={}", userId);
            throw new SubstituteException("接单失败，用户不存在");
        }

        checkUserInfo(user);
        //todo 是否只能接同校订单
//        User publisher =userService.findUserById(indent.getPublisherId());
//        if (!user.getSchoolId().equals(publisher.getSchoolId())){
//            log.error("【接单】接单失败，只能接同校订单，user={}", user);
//            throw new SubstituteException("接单失败，只能接同校订单");
//        }

        indent.setPerformerId(userId);
        indent.setIndentState(IndentStateEnum.PERFORMING);
        indentMapper.updateByPrimaryKeySelective(indent);
    }

    @Override
    public void arrivedIndent(Integer indentId, String userId) {
        Indent indent = indentMapper.findByIndentId(indentId);

        if (indent == null || indent.getPerformerId() == null || indent.getIndentState() != IndentStateEnum.PERFORMING) {
            log.error("【送达订单】送达订单失败，订单信息有误，indent={}", indent);
            throw new SubstituteException(ResultEnum.INDENT_STATE_ERROR);
        }

        if (!userId.equals(indent.getPerformerId())) {
            log.error("【送达订单】送达订单失败，该用户非接单人，userId={}，indent={}", userId, indent);
            throw new SubstituteException("送达订单失败，该用户无权限，非接单人");
        }

        // 该订单已送达
        indent.setIndentState(IndentStateEnum.ARRIVED);
        indentMapper.updateByPrimaryKeySelective(indent);
    }

    @Override
    public BigDecimal finishedIndent(Integer indentId, String userId) {
        // 检验参数是否正确，订单状态是否正确
        Indent indent = indentMapper.findByIndentId(indentId);
        if (indent == null || indent.getIndentState() == IndentStateEnum.CANCELED
                || indent.getIndentState() == IndentStateEnum.WAIT_FOR_PERFORMER
                || indent.getIndentState() == IndentStateEnum.COMPLETED) {
            log.error("【完结订单】完结订单失败，订单状态有误，indent={}", indent);
            throw new SubstituteException(ResultEnum.INDENT_STATE_ERROR);
        }

        if (!userId.equals(indent.getPublisherId())) {
            log.error("【完结订单】完结订单失败，该用户非下单人，userId={}，indent={}", userId, indent);
            throw new SubstituteException("完结订单失败，该用户无权限，非下单人");
        }

        User performer = userService.getUserById(indent.getPerformerId());
        if (performer == null) {
            log.error("完结订单失败，该订单未被接，indent={}", indent);
            throw new SubstituteException(ResultEnum.INDENT_STATE_ERROR);
        }

        BigDecimal income = BigDecimal.valueOf(indent.getIndentPrice());
        BigDecimal oldBalance = performer.getBalance();
        performer.setBalance(oldBalance.add(income));
        performer.setAllIncome(oldBalance.add(income));
        userService.saveUser(performer);

        indent.setIndentState(IndentStateEnum.COMPLETED);
        indentMapper.updateByPrimaryKeySelective(indent);
        return BigDecimal.valueOf(indent.getIndentPrice());
    }

    @Override
    public void canceledIndent(Integer indentId, String userId) {
        // 检验参数是否正确，订单状态是否正确
        Indent indent = indentMapper.findByIndentId(indentId);

        if (indent == null || indent.getIndentState() == IndentStateEnum.COMPLETED
                || indent.getIndentState() == IndentStateEnum.CANCELED) {
            log.error("【取消订单】取消订单失败，订单状态有误，indent={}", indent);
            throw new SubstituteException(ResultEnum.INDENT_STATE_ERROR);
        }

        if (userId.equals(indent.getPerformerId())) {
            // 接单人取消订单
            // 若为待接单状态则无权限取消
            if (indent.getIndentState() == IndentStateEnum.WAIT_FOR_PERFORMER) {
                log.error("【取消订单】取消订单失败，订单状态有误，indent={}", indent);
            }
            // 将订单重新变为待接状态
            log.info("【取消订单】接单人取消订单，userId={}，indent={}", userId, indent);
            indent.setIndentState(IndentStateEnum.WAIT_FOR_PERFORMER);
//            indent.setUrgentType(UrgentTypeEnum.CANCEL.getCode().byteValue());
            indent.setPerformerId(null);
            indentMapper.updateByPrimaryKeySelective(indent);
            return;
        }

        if (!userId.equals(indent.getPublisherId())) {
            log.error("【取消订单】取消订单失败，操作用户非下单人，userId={}，indentId={}", userId, indent);
            throw new SubstituteException("取消订单失败，操作用户无权限，非下单人");
        }

        // 如果取消订单的用户是下单人，退钱
        User user = userService.getUserById(indent.getPublisherId());
        user.setBalance(user.getBalance().add(BigDecimal.valueOf(indent.getTotalPrice())));
        userService.saveUser(user);

        // 修改订单状态
        indent.setIndentState(IndentStateEnum.CANCELED);
        indentMapper.updateByPrimaryKeySelective(indent);
    }

    @Override
    public IndentListVO getUserPublishedIndent(String userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Indent> indents = indentMapper.findByPublisherId(userId);
        PageInfo info = new PageInfo(indents);
        boolean hasNextPage = info.isHasNextPage();
        // todo 重新写一个 IndentListVO，里面包含了 hasNextPage
        return new IndentListVO(indent2VO(indents), hasNextPage);
    }

    @Override
    public IndentListVO getUserPerformedIndent(String userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Indent> indents = indentMapper.findByPerformerId(userId);
        PageInfo info = new PageInfo(indents);
        boolean hasNextPage = info.isHasNextPage();
        return new IndentListVO(indent2VO(indents), hasNextPage);
    }

    @Override
    public IndentListVO getWait(Integer sortType, GenderEnum sexType, int pageNum, int pageSize) {
        IndentSortTypeEnum sortTypeEnum = CommonUtil.getEnum(sortType, IndentSortTypeEnum.class);
        if (sortTypeEnum == null) {
            log.error("【获取订单列表】获取失败，sortType有误，sortType={}", sortType);
            throw new SubstituteException("sortType 有误");
        }

        if (sortType == null) {
            log.error("【获取订单列表】获取失败，sexType为空");
            throw new SubstituteException("sexType 为空");
        }

        GenderEnum excludeGender;
        switch (sexType) {
            case MALE:
                excludeGender = GenderEnum.FEMALE;
                break;
            case FEMALE:
                excludeGender = GenderEnum.MALE;
                break;
            default:
                // 未知或异常字段时报错
                log.error("【获取订单列表】获取失败，sexType 有误，sexType={}", sexType);
                throw new SubstituteException("sexType 有误");
        }

        List<Indent> indents;

        // done 测试有分支的情况下能否在分支外使用 startPage
        // done 答：可以
        PageHelper.startPage(pageNum, pageSize);
        switch (sortTypeEnum) {
            case SORT_BY_TIME:
                indents = indentMapper.findAllByIndentStateAndRequireGenderNotOrderByCreateTimeDesc(
                        IndentStateEnum.WAIT_FOR_PERFORMER, excludeGender
                );
                break;
            case SORT_BY_PRICE:
                indents = indentMapper.findAllByIndentStateAndRequireGenderNotOrderByIndentPriceDesc(
                        IndentStateEnum.WAIT_FOR_PERFORMER, excludeGender
                );
                break;
            case SORT_BY_DEFAULT:
                List<Indent> temp = indentMapper.findAllByIndentStateAndRequireGenderNot(
                        IndentStateEnum.WAIT_FOR_PERFORMER, excludeGender
                );
                //随机打乱一下
                Random random = new Random();
                indents = new ArrayList<>();
                while (temp.size() > 0) {
                    int i = random.nextInt(temp.size());
                    indents.add(temp.get(i));
                    temp.remove(i);
                }
                break;
            default:
                log.error("[获取订单列表]获取失败，sortType有误，sortType={}", sortType);
                throw new SubstituteException("sortType有误");
        }
        PageInfo info = new PageInfo(indents);
        boolean hasNextPage = info.isHasNextPage();
        List<IndentVO> indentVOS = indent2VO(indents);
        indentVOS.forEach(x -> {
            //将隐私信息置空
            x.setSecretText(null);
        });

        return new IndentListVO(indent2VO(indents), hasNextPage);
    }

    @Override
    public IndentVO getIndentDetail(Integer indentId, String userId) {
        Indent indent = indentMapper.findByIndentId(indentId);
        if (indent == null) {
            log.error("【获取订单详情】获取订单详情失败，订单 id 不存在，indentId={}", indentId);
            throw new SubstituteException(ResultEnum.INDENT_NOT_EXISTS);
        }

        try {
            IndentVO indentVO = indent2VO(indent);
            if (!userId.equals(indent.getPerformerId()) && !userId.equals(indent.getPublisherId())) {
                indentVO.setSecretText(null);
            }
            return indentVO;
        } catch (CheckException e) {
            log.error("【获取订单详情】获取失败，订单信息有误，message={}，indent={}", e.getMessage(), indent);
            throw new SubstituteException(e.getMessage());
        }
    }
}
