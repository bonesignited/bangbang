package com.zlx.bangbang.domain;

import java.util.Date;

public class CouponRecord {
    private Integer id;

    private Integer couponId;

    private String ownerId;

    private Boolean isUsed;

    private Date invalidTime;

    public CouponRecord(Integer id, Integer couponId, String ownerId, Boolean isUsed, Date invalidTime) {
        this.id = id;
        this.couponId = couponId;
        this.ownerId = ownerId;
        this.isUsed = isUsed;
        this.invalidTime = invalidTime;
    }

    public CouponRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }
}