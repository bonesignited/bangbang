package com.zlx.bangbang.domain;

import java.math.BigDecimal;

public class CountInfo {
    private Integer countId;

    private Integer schoolId;

    private Integer countDate;

    private Integer newIndent;

    private Integer finishedIndent;

    private BigDecimal income;

    private Integer loginUser;

    public CountInfo(Integer countId, Integer schoolId, Integer countDate, Integer newIndent, Integer finishedIndent, BigDecimal income, Integer loginUser) {
        this.countId = countId;
        this.schoolId = schoolId;
        this.countDate = countDate;
        this.newIndent = newIndent;
        this.finishedIndent = finishedIndent;
        this.income = income;
        this.loginUser = loginUser;
    }

    public CountInfo() {
        super();
    }

    public Integer getCountId() {
        return countId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getCountDate() {
        return countDate;
    }

    public void setCountDate(Integer countDate) {
        this.countDate = countDate;
    }

    public Integer getNewIndent() {
        return newIndent;
    }

    public void setNewIndent(Integer newIndent) {
        this.newIndent = newIndent;
    }

    public Integer getFinishedIndent() {
        return finishedIndent;
    }

    public void setFinishedIndent(Integer finishedIndent) {
        this.finishedIndent = finishedIndent;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public Integer getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(Integer loginUser) {
        this.loginUser = loginUser;
    }
}