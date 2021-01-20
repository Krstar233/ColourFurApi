package com.colourfur.security.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.colourfur.security.entity.User;
import com.colourfur.security.mapper.UserMapper;
import com.colourfur.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}