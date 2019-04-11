package com.zlx.bangbang.domain;

import com.zlx.bangbang.enums.GenderEnum;
import com.zlx.bangbang.enums.IndentStateEnum;
import com.zlx.bangbang.enums.IndentTypeEnum;
import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@ToString
@Builder
public class Indent {
    private Integer indentId;

    private String performerId;

    private String publisherId;

    private Integer couponId;

    private Integer couponPrice;

    private IndentTypeEnum indentType;

    private GenderEnum requireGender;

    private String indentContent;

    private Integer indentPrice;

    private Integer totalPrice;

    private Byte urgentType;

    private Boolean isSolved;

    private IndentStateEnum indentState;

    private String secretText;

    private String takeGoodAddress;

    private Integer shippingAddressId;

    private BigDecimal goodPrice;

    private Date createTime;

    private Date updateTime;

    public Indent(Integer indentId, String performerId, String publisherId, Integer couponId, Integer couponPrice, IndentTypeEnum indentType, GenderEnum requireGender, String indentContent, Integer indentPrice, Integer totalPrice, Byte urgentType, Boolean isSolved, IndentStateEnum indentState, String secretText, String takeGoodAddress, Integer shippingAddressId, BigDecimal goodPrice, Date createTime, Date updateTime) {
        this.indentId = indentId;
        this.performerId = performerId;
        this.publisherId = publisherId;
        this.couponId = couponId;
        this.couponPrice = couponPrice;
        this.indentType = indentType;
        this.requireGender = requireGender;
        this.indentContent = indentContent;
        this.indentPrice = indentPrice;
        this.totalPrice = totalPrice;
        this.urgentType = urgentType;
        this.isSolved = isSolved;
        this.indentState = indentState;
        this.secretText = secretText;
        this.takeGoodAddress = takeGoodAddress;
        this.shippingAddressId = shippingAddressId;
        this.goodPrice = goodPrice;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Indent() {
        super();
    }

    public Integer getIndentId() {
        return indentId;
    }

    public void setIndentId(Integer indentId) {
        this.indentId = indentId;
    }

    public String getPerformerId() {
        return performerId;
    }

    public void setPerformerId(String performerId) {
        this.performerId = performerId == null ? null : performerId.trim();
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId == null ? null : publisherId.trim();
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Integer couponPrice) {
        this.couponPrice = couponPrice;
    }

    public IndentTypeEnum getIndentType() {
        return indentType;
    }

    public void setIndentType(IndentTypeEnum indentType) {
        this.indentType = indentType;
    }

    public GenderEnum getRequireGender() {
        return requireGender;
    }

    public void setRequireGender(GenderEnum requireGender) {
        this.requireGender = requireGender;
    }

    public String getIndentContent() {
        return indentContent;
    }

    public void setIndentContent(String indentContent) {
        this.indentContent = indentContent == null ? null : indentContent.trim();
    }

    public Integer getIndentPrice() {
        return indentPrice;
    }

    public void setIndentPrice(Integer indentPrice) {
        this.indentPrice = indentPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Byte getUrgentType() {
        return urgentType;
    }

    public void setUrgentType(Byte urgentType) {
        this.urgentType = urgentType;
    }

    public Boolean getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(Boolean isSolved) {
        this.isSolved = isSolved;
    }

    public IndentStateEnum getIndentState() {
        return indentState;
    }

    public void setIndentState(IndentStateEnum indentState) {
        this.indentState = indentState;
    }

    public String getSecretText() {
        return secretText;
    }

    public void setSecretText(String secretText) {
        this.secretText = secretText == null ? null : secretText.trim();
    }

    public String getTakeGoodAddress() {
        return takeGoodAddress;
    }

    public void setTakeGoodAddress(String takeGoodAddress) {
        this.takeGoodAddress = takeGoodAddress == null ? null : takeGoodAddress.trim();
    }

    public Integer getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Integer shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
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