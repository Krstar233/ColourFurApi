package com.colourfur.userservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
}
