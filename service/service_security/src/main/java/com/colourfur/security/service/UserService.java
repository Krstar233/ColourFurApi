package com.colourfur.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.colourfur.security.entity.User;

public interface UserService extends IService<User> {
    User selectByUsername(String username);
}
