package com.zlx.bangbang.utils;

import com.zlx.bangbang.dto.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResultUtil<T> {
    public static ResponseEntity success(Integer code, String msg, Object data) {
        return new ResponseEntity<>(new ResultDTO<>(code, msg, data), HttpStatus.OK);
    }

    public static ResponseEntity success(Object data) {
        return success(0, "请求成功", data);
    }

    public static ResponseEntity success(String msg, Object data) {
        return success(0, msg, data);
    }

    public static ResponseEntity success() {
        return success(null);
    }

    public static ResponseEntity error(Integer code, String msg, HttpStatus status) {
        return new ResponseEntity<>(new ResultDTO<>(code, msg, null), status);
    }

    public static ResponseEntity error(Integer code, String msg) {
        return error(code, msg, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity error() {
        return error(-1, "请求失败", HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity error(String msg) {
        return error(-1, msg);
    }
}
