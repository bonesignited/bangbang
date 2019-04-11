package com.zlx.bangbang.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class Address {
    private Integer id;

    private Long phone;

    private String userName;

    private String address;

    private String userId;

    private Boolean isDeleted;

    public Address(Integer id, Long phone, String userName, String address, String userId, Boolean isDeleted) {
        this.id = id;
        this.phone = phone;
        this.userName = userName;
        this.address = address;
        this.userId = userId;
        this.isDeleted = isDeleted;
    }

    public Address() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}