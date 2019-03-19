package com.zlx.bangbang.exceptions;

import com.zlx.bangbang.enums.ResultEnum;

public class SubtituteException extends RuntimeException {
    /**
     * note 自定义异常调用 super() 设置异常信息，
     * 可以自行定义如 code 等其它字段
     */

    private Integer code;

    public SubtituteException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SubtituteException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public SubtituteException(String message) {
        super(message);
        this.code = -1;
    }
}
