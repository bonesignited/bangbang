package com.zlx.bangbang.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zlx.bangbang.enums.GenderEnum;
import com.zlx.bangbang.utils.Date2LongSerializer;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@Builder
public class User {
    private String id;

    private String openid;

    private String masterId;

    private String userName;

    private String avatar;

    private GenderEnum gender;

    private BigDecimal balance;

    private BigDecimal allIncome;

    private BigDecimal masterIncome;

    private String trueName;

    private Long phone;

    private Integer schoolId;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private String userPass;

    public User(String id, String openid, String masterId, String userName, String avatar, GenderEnum gender, BigDecimal balance, BigDecimal allIncome, BigDecimal masterIncome, String trueName, Long phone, Integer schoolId, Date createTime, Date updateTime, String userPass) {
        this.id = id;
        this.openid = openid;
        this.masterId = masterId;
        this.userName = userName;
        this.avatar = avatar;
        this.gender = gender;
        this.balance = balance;
        this.allIncome = allIncome;
        this.masterIncome = masterIncome;
        this.trueName = trueName;
        this.phone = phone;
        this.schoolId = schoolId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userPass = userPass;
    }

    public User() {
        super();
    }
}