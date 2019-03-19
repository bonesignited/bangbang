package com.zlx.bangbang.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultDTO<T> implements Serializable {
    private static final long serialVersionUID = -7086207726234499384L;

    private int code;

    private String msg;

    private T data;


    public ResultDTO() {
    }

    public ResultDTO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
