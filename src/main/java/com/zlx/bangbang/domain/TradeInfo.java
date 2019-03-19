package com.zlx.bangbang.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TradeInfo {
    private Integer tradeId;

    private Integer couponId;

    private BigDecimal couponPrice;

    private Date createTime;

    private Integer indentId;

    private BigDecimal indentPrice;

    private BigDecimal totalPrice;

    private Date updateTime;

    public TradeInfo(Integer tradeId, Integer couponId, BigDecimal couponPrice, Date createTime, Integer indentId, BigDecimal indentPrice, BigDecimal totalPrice, Date updateTime) {
        this.tradeId = tradeId;
        this.couponId = couponId;
        this.couponPrice = couponPrice;
        this.createTime = createTime;
        this.indentId = indentId;
        this.indentPrice = indentPrice;
        this.totalPrice = totalPrice;
        this.updateTime = updateTime;
    }

    public TradeInfo() {
        super();
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIndentId() {
        return indentId;
    }

    public void setIndentId(Integer indentId) {
        this.indentId = indentId;
    }

    public BigDecimal getIndentPrice() {
        return indentPrice;
    }

    public void setIndentPrice(BigDecimal indentPrice) {
        this.indentPrice = indentPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}