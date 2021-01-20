package com.colourfur.security.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

@Data
public class User{
    private String username;
    private String password;
    private String nickname;
    @TableLogic
    private Boolean isDeleted;
    private Integer clfGroupId; //权限组id
    private Date gmtCreate;
    private Date gmtUpdate;
    private Long id;
}
