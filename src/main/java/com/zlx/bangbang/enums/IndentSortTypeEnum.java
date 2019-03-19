package com.zlx.bangbang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IndentSortTypeEnum {
    SORT_BY_DEFAULT(0, "根据默认排序"),
    SORT_BY_TIME(10, "根据创建时间排序"),
    SORT_BY_PRICE(20, "根据价格排序"),
    ;

    private Integer code;
    private String message;
}
