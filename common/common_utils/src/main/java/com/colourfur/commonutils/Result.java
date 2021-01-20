package com.colourfur.commonutils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data;

    private Result(){}

    public static Result ok(){
        Result rs = new Result();
        rs.success = true;
        rs.code = ResultCode.SUCCESS;
        rs.message = "成功！";
        rs.data = new HashMap<>();
        return rs;
    }

    public static Result error(){
        Result rs = new Result();
        rs.success = false;
        rs.code = ResultCode.FAILED;
        rs.message = "失败！";
        rs.data = new HashMap<>();
        return rs;
    }

    public Result success(Boolean success){
        this.success = success;
        return this;
    }

    public Result message(String message){
        this.message = message;
        return this;
    }

    public Result code(Integer code){
        this.code = code;
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.data = map;
        return this;
    }

}
