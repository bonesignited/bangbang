package com.zlx.bangbang.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {
    private Integer addressId;

    private String address;

    private Long phone;

    private String userName;

    private String userId;
}
