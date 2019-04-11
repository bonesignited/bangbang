package com.zlx.bangbang.dto;

import com.zlx.bangbang.enums.GenderEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class ModifyUserInfoDTO {

    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank
    private String trueName;
    @NotNull
    private Long phone;
    @NotNull
    private Integer schoolId;
    @NotNull
    private GenderEnum gender;
    //短信验证码
    @NotBlank
    private String checkCode;


    public ModifyUserInfoDTO() {
    }

}
