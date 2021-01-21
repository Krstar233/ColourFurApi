package com.colourfur.userservice.controller;

import com.colourfur.userservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
public class HelloController {
    @Autowired
    UserService userService;

    @ApiOperation("测试是否已经携带token")
    @GetMapping("/hello")
    public String hello(
            @ApiIgnore
            @RequestAttribute String uid) {
        return "hello," + userService.getById(uid).getNickname() + "!";
    }
}