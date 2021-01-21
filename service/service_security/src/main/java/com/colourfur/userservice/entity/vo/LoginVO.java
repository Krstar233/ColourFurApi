package com.colourfur.userservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginVO {
    @ApiModelProperty(value = "用户名", example = "root")
    private String username;
    @ApiModelProperty(value = "密码", example = "root")
    private String password;
}
