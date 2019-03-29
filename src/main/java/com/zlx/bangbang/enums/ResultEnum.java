package com.zlx.bangbang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultEnum{
    SUCCESS(0, "成功"),
    INNER_ERROR(-1, "服务器异常"),

    // -101xx 为用户错误
    USER_NOT_EXISTS(-10101, "用户不存在"),

    // -102xx 为参数错误
    PARAM_ERROR(-10201, "参数格式有误"),
    PARAM_NULL_ERROR(-10202, "必填参数为空"),

    // -103xx 为订单错误
    INDENT_NOT_EXISTS(-10301, "订单不存在"),
    INDENT_STATE_ERROR(-10302, "订单状态有有误"),

    // -104xx 为金额错误
    WX_NOTIFY_MONEY_VERIFY_ERROR(-10401, "微信异步通知金额校验不通过"),

    // -105xx 为优惠券有关
    COUPON_NOT_EXISTS(-10501, "优惠券不存在"),
    ;


    private Integer code;
    private String msg;
}
