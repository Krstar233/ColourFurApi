package com.colourfur.security.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProfileEditVO {
    @ApiModelProperty(value = "修改密码", example = "new password")
    private String password;
    @ApiModelProperty(value = "修改昵称", example = "new nickname")
    private String nickname;
}
