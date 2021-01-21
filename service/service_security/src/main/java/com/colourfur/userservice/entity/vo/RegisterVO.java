package com.colourfur.userservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterVO {
    @ApiModelProperty(value = "用户名", example = "krstar@colourfur.com")
    private String username;
    @ApiModelProperty(value = "密码", example = "12345678")
    private String password;
    @ApiModelProperty(value = "昵称", example = "昵称")
    private String nickname;
}
