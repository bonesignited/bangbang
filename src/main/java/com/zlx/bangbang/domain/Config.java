package com.zlx.bangbang.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Config {
    private Boolean id;

    private BigDecimal companyRatio;

    private BigDecimal masterRatio;

    private BigDecimal performerRatio;

    private Integer leastPrice;

    private Integer overTime;

    private Date updateTime;

    public Config(Boolean id, BigDecimal companyRatio, BigDecimal masterRatio, BigDecimal performerRatio, Integer leastPrice, Integer overTime, Date updateTime) {
        this.id = id;
        this.companyRatio = companyRatio;
        this.masterRatio = masterRatio;
        this.performerRatio = performerRatio;
        this.leastPrice = leastPrice;
        this.overTime = overTime;
        this.updateTime = updateTime;
    }

    public Config() {
        super();
    }

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) {
        this.id = id;
    }

    public BigDecimal getCompanyRatio() {
        return companyRatio;
    }

    public void setCompanyRatio(BigDecimal companyRatio) {
        this.companyRatio = companyRatio;
    }

    public BigDecimal getMasterRatio() {
        return masterRatio;
    }

    public void setMasterRatio(BigDecimal masterRatio) {
        this.masterRatio = masterRatio;
    }

    public BigDecimal getPerformerRatio() {
        return performerRatio;
    }

    public void setPerformerRatio(BigDecimal performerRatio) {
        this.performerRatio = performerRatio;
    }

    public Integer getLeastPrice() {
        return leastPrice;
    }

    public void setLeastPrice(Integer leastPrice) {
        this.leastPrice = leastPrice;
    }

    public Integer getOverTime() {
        return overTime;
    }

    public void setOverTime(Integer overTime) {
        this.overTime = overTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}