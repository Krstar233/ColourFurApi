package com.colourfur.chatservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class FindMessageQuery {
    @ApiModelProperty(value = "查询对象uid", example = "1352230441593839618", required = true)
    private String findUid;
    @ApiModelProperty(value = "起始时间", example = "2020-1-10 00:00:00")
    private Date begin;
    @ApiModelProperty(value = "结束时间", example = "2021-10-10 00:00:00")
    private Date end;
    @ApiModelProperty(value = "查询内容", example = "hi")
    private String content;
    @ApiModelProperty(value = "是否已读", example = "true")
    private Boolean readStatus;
    @ApiModelProperty(value = "分页（当前页数）", example = "1")
    private Integer current;
    @ApiModelProperty(value = "每页限制查询的聊天信息条数", example = "10")
    private Integer size;
}
