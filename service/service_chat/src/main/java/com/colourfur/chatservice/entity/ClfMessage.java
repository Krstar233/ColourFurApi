package com.colourfur.chatservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author krits
 * @since 2021-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ClfMessage对象", description="")
public class ClfMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private Date gmtCreate;

    private Date gmtUpdate;

    private String fromUid;

    private String toUid;

    private String content;
    
    private Boolean readStatus; // 对方是否已读

    private String ownerUid;

    @TableLogic
    private Boolean isDeleted;

    public ClfMessage(){}

    public ClfMessage(String fromUid, String toUid, String content) {
        this.fromUid = fromUid;
        this.toUid = toUid;
        this.content = content;
        this.readStatus = false;
        this.isDeleted = false;
        this.gmtCreate = new Date();
        this.gmtUpdate = new Date();
    }
}
