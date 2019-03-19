package com.zlx.bangbang.exceptions;

public class NoAuthenticationException extends RuntimeException {
    public NoAuthenticationException() {
        super("");
    }

    public NoAuthenticationException(String message) {
        super(message);
    }
}
