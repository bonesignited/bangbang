package com.zlx.bangbang.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zlx.bangbang.utils.Date2LongSerializer;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

public class CouponInfo {
    private Integer couponId;

    private Integer leastPrice;

    private Integer reducePrice;


    private Boolean isDeleted;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date validTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date invalidTime;

    private byte[] picture;

    public CouponInfo(Integer couponId, Integer leastPrice, Integer reducePrice, Boolean isDeleted, Date validTime, Date invalidTime, byte[] picture) {
        this.couponId = couponId;
        this.leastPrice = leastPrice;
        this.reducePrice = reducePrice;
        this.isDeleted = isDeleted;
        this.validTime = validTime;
        this.invalidTime = invalidTime;
        this.picture = picture;
    }

    public CouponInfo(Integer couponId, Integer leastPrice, Integer reducePrice, Boolean isDeleted, Date validTime, Date invalidTime) {
        this.couponId = couponId;
        this.leastPrice = leastPrice;
        this.reducePrice = reducePrice;
        this.isDeleted = isDeleted;
        this.validTime = validTime;
        this.invalidTime = invalidTime;
    }

    public CouponInfo() {
        super();
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getLeastPrice() {
        return leastPrice;
    }

    public void setLeastPrice(Integer leastPrice) {
        this.leastPrice = leastPrice;
    }

    public Integer getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(Integer reducePrice) {
        this.reducePrice = reducePrice;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}