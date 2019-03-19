package com.zlx.bangbang.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum UrgentTypeEnum {


    NOT_URGENT(0, "未加急"),

    OVERTIME(1, "超时"),

    CANCEL(2, "退单");

    private Integer code;
    private String message;
}

