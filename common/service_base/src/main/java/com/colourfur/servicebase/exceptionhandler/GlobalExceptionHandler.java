package com.colourfur.servicebase.exceptionhandler;

import com.colourfur.servicebase.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.colourfur.commonutils.Result;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
//        log.error(e.getMessage());
        return Result.error().message("发生异常！");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result myError(Exception e){
        e.printStackTrace();
//        log.error(e.getMessage());
        return Result.error().message(e.getMessage());
    }
}
