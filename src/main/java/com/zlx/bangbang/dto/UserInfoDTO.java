package com.zlx.bangbang.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = -5499040268895190079L;

    private String id;

    private String userName;

    private String avatar;

    private String gender;

    private String trueName;

    private Long phone;

    private Integer schoolId;

    private String userPass;
}
