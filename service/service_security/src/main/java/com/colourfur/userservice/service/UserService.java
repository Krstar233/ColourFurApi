package com.colourfur.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.colourfur.userservice.entity.User;

public interface UserService extends IService<User> {
    User selectByUsername(String username);
}
