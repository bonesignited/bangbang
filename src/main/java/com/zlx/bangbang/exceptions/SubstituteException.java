package com.zlx.bangbang.exceptions;

import com.zlx.bangbang.enums.ResultEnum;
import lombok.Getter;

@Getter
public class SubstituteException extends RuntimeException {
    /**
     * note 自定义异常调用 super() 设置异常信息，
     * 可以自行定义如 code 等其它字段
     */

    private Integer code;

    public SubstituteException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SubstituteException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public SubstituteException(String message) {
        super(message);
        this.code = -1;
    }
}
