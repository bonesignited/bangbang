package com.zlx.bangbang.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 发布订单验证表单
 */
@Data
public class IndentCreateForm {
    // 订单类型，帮我够，帮我递，随意帮
    @NotBlank(message = "订单类型不能为空")
    private String indentType;

    //要求性别，男，女，不限
    @NotBlank(message = "需求性别不能为空")
    private String requireGender;

    // 下单用户id
    @NotNull(message = "下单用户id不能为空")
    private String publisherId;

    @NotBlank(message = "订单内容不能为空")
    private String indentContent;

    @NotNull(message = "订单悬赏金不能为空")
    private Integer indentPrice;

    // 取货地址，订单类型非随意帮时必填
    private String takeGoodAddress;

    // 送达地址id，订单类型为帮我递时必填
    private Integer shippingAddressId;

    // 物品金额，订单类型为帮我购时必填
    private BigDecimal goodPrice;

    // 隐私消息，订单类型为帮我递时可填
    private String secretText;

    // 优惠券 id，可能为空
    private Integer couponId;
}
