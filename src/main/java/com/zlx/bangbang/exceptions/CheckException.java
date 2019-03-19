package com.zlx.bangbang.exceptions;

import com.zlx.bangbang.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 验证信息错误类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CheckException extends Exception {

    // 错误码
    private Integer code;

    public CheckException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public CheckException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public CheckException(String message) {
        super(message);
        this.code = -1;
    }
}
