package com.zlx.bangbang.domain;

import java.util.Date;

public class WithdrawRequest {
    private Integer withdrawId;

    private String userId;

    private Boolean isSolved;

    private Date createTime;

    public WithdrawRequest(Integer withdrawId, String userId, Boolean isSolved, Date createTime) {
        this.withdrawId = withdrawId;
        this.userId = userId;
        this.isSolved = isSolved;
        this.createTime = createTime;
    }

    public WithdrawRequest() {
        super();
    }

    public Integer getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Integer withdrawId) {
        this.withdrawId = withdrawId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Boolean getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(Boolean isSolved) {
        this.isSolved = isSolved;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}