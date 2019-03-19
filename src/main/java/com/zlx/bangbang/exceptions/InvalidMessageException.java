package com.zlx.bangbang.exceptions;

import com.zlx.bangbang.constants.Constant;

public class InvalidMessageException extends RuntimeException {
    public InvalidMessageException() {
        super(Constant.INVALID_MESSAGE);
    }

    public InvalidMessageException(String message) {
        super(message);
    }
}
