package com.zlx.bangbang.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zlx.bangbang.enums.GenderEnum;
import com.zlx.bangbang.utils.Date2LongSerializer;
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

    private GenderEnum gender;

    private String trueName;

    private Long phone;

    private String school;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private BigDecimal balance;

    private BigDecimal allIncome;
}
