package com.colourfur.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colourfur.security.entity.User;
import com.colourfur.security.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan({"fun.krits"})
public class UserTest {
    @Autowired
    UserService userService;

    @Test
    public void testUserSearch(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "root");
        User user = userService.getOne(wrapper);
        System.out.println(user);
    }
}
