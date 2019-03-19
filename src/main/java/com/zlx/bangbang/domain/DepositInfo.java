package com.zlx.bangbang.domain;

import java.util.Date;

public class DepositInfo {
    private String depositId;

    private String depositOpenid;

    private Integer depositMoney;

    private Boolean isSuccess;

    private Date createTime;

    private Date updateTime;

    public DepositInfo(String depositId, String depositOpenid, Integer depositMoney, Boolean isSuccess, Date createTime, Date updateTime) {
        this.depositId = depositId;
        this.depositOpenid = depositOpenid;
        this.depositMoney = depositMoney;
        this.isSuccess = isSuccess;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public DepositInfo() {
        super();
    }

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId == null ? null : depositId.trim();
    }

    public String getDepositOpenid() {
        return depositOpenid;
    }

    public void setDepositOpenid(String depositOpenid) {
        this.depositOpenid = depositOpenid == null ? null : depositOpenid.trim();
    }

    public Integer getDepositMoney() {
        return depositMoney;
    }

    public void setDepositMoney(Integer depositMoney) {
        this.depositMoney = depositMoney;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
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
}