package com.zlx.bangbang.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 订单、用户 id
 */
@Data
public class IndentUserForm {
    // 订单 id
    @NotNull(message = "订单id不能为空")
    private Integer indentId;

    // 用户 id
    @NotBlank(message = "用户 id 不能为空")
    private String userId;
}
