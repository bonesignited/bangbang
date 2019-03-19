package com.zlx.bangbang.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CouponUserForm {
    @NotNull(message = "用户 id 不能为空")
    String userId;

    @NotNull(message = "优惠券 id 不能为空")
    Integer couponId;
}
