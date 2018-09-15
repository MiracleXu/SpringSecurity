package com.dzc.springbootsecurity.mapper;

import com.dzc.springbootsecurity.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    User findByUserName(String username);
}