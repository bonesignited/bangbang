package com.zlx.bangbang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultEnum implements CodeEnum{
    SUCCESS(0, "成功"),
    INNER_ERROR(-1, "服务器异常"),

    // -101xx 为用户错误
    USER_NOT_EXISTS(-10101, "用户不存在"),
    NEED_LOGIN(-10102, "用户未登录，请先登录"),
    PWD_ERROR(-10103, "密码错误"),
    SCHOOL_ERROR(-10104, "该学校不存在，请填入其它学校"),

    // -102xx 为参数错误
    PARAM_ERROR(-10201, "参数格式有误"),
    PARAM_NULL_ERROR(-10202, "必填参数为空"),

    // -103xx 为订单错误
    INDENT_NOT_EXISTS(-10301, "订单不存在"),
    INDENT_STATE_ERROR(-10302, "订单状态有有误"),


    // -105xx 为优惠券有关
    COUPON_NOT_EXISTS(-10501, "优惠券不存在"),
    ;


    private Integer code;
    private String msg;
}
