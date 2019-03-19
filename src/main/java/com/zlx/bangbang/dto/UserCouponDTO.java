package com.zlx.bangbang.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserCouponDTO implements Serializable {
    private static long serialVersionUID = -620864047986869128L;

    private Integer couponId;

    // 最小满减金额，单位元
    private Integer leastPrice;

    // 可减金额，单位元
    private Integer reducePrice;

    private Date validTime;

    // 失效时间
    private Date invalidTime;

    private String pictureLink;

    public UserCouponDTO() {
    }
}
