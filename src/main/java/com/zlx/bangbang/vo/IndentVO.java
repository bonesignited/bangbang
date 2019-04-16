package com.zlx.bangbang.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zlx.bangbang.enums.GenderEnum;
import com.zlx.bangbang.enums.IndentStateEnum;
import com.zlx.bangbang.enums.IndentTypeEnum;
import com.zlx.bangbang.utils.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class IndentVO {
    //订单id
    private Integer indentId;

    //接单人人id
    private String performerId;

    //下单用户id
    private String publisherId;

    //订单类型，帮我购：HELP_BUY，帮我递：HELP_SEND，随意帮：HELP_OTHER
    private IndentTypeEnum indentType;

    //订单要求性别，男：”MALE”,女：”FEMALE”,不限：”NO_LIMITED”
    private GenderEnum requireGender;

    //任务内容
    private String indentContent;

    //订单悬赏金
    private Integer indentPrice;

    //订单优惠额，单位元，默认0元
    private Integer couponPrice;

    //实付金额,单位元
    private Integer totalPrice;

    //订单状态,待接单：WAIT_FOR_PERFORMER，执行中：PERFORMING，货物已送达：ARRIVED，已完成：COMPLETED
    private IndentStateEnum indentState;

    //取货地址，订单类型非随意帮时必填
    private String takeGoodAddress;

    //隐私信息，仅订单类型为帮我递时非空
    private String secretText;

    //物品金额，仅订单类型为帮我购时非空
    private BigDecimal goodPrice;

//======================================需要自己拼接的字段========================================================

    //联系人电话,不能用publisherId查，因为可能不同
    private Long publisherPhone;

    //接单人电话
    private Long performerPhone;

    //送达地点，订单类型为帮我递时必填
    private String shippingAddress;

    //下单人性别，男：”MALE”,女：”FEMALE”,未知：”NO_LIMITED”
    private GenderEnum publisherGender;

    //下单人学校id
    private Integer publisherSchoolId;

    //下单人学校名称
    private String publisherSchool;

    //下单人头像URL
    private String publisherAvatar;

    //联系人真实姓名,不能用publisherId查，因为可能不同，订单类型非随意帮时必填
    private String publisherName;

    //下单人微信昵称
    private String publisherNickName;

    //接单人性别，男：”MALE”,女：”FEMALE”,未知：”NO_LIMITED”
    private GenderEnum performerGender;

    //接单人学校Id
    private Integer performerSchoolId;

    //接单人学校名称
    private String performerSchool;

    //接单人头像URL
    private String performerAvatar;

    //接单人微信昵称
    private String performerNickName;

    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    //最近更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}

