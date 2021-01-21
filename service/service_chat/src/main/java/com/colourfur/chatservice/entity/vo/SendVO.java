package com.colourfur.chatservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SendVO {
    @ApiModelProperty(value = "接收者uid", example = "1351791644947128321")
    private String toUid;
    @ApiModelProperty(value = "发送内容", example = "Hi~")
    private String content;
}
