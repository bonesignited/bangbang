package com.zlx.bangbang.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterDTO implements Serializable {
    public static final long serialVersionUID = -3462662826460436388L;

    private String username;
    private String password;

}
