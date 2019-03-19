package com.zlx.bangbang.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {
    public static final long serialVersionUID = -3953570450949018322L;

    String id;
    String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
