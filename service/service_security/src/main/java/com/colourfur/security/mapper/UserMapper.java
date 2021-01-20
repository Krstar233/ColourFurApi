package com.colourfur.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.colourfur.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    User selectByUsername(String username);
}
