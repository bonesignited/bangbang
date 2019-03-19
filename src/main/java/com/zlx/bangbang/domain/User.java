package com.zlx.bangbang.domain;

import com.zlx.bangbang.enums.GenderEnum;

import java.math.BigDecimal;
import java.util.Date;

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

    private Date createTime;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId == null ? null : masterId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAllIncome() {
        return allIncome;
    }

    public void setAllIncome(BigDecimal allIncome) {
        this.allIncome = allIncome;
    }

    public BigDecimal getMasterIncome() {
        return masterIncome;
    }

    public void setMasterIncome(BigDecimal masterIncome) {
        this.masterIncome = masterIncome;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}