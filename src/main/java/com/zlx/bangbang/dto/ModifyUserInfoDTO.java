package com.zlx.bangbang.dto;

import com.zlx.bangbang.enums.GenderEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class ModifyUserInfoDTO {

    @NotBlank
    private String trueName;
    @NotNull
    private Long phone;
    @NotNull
    private String schoolName;
    @NotNull
    private GenderEnum gender;

    public ModifyUserInfoDTO() {
    }

}
