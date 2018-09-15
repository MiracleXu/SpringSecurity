package com.dzc.springbootsecurity.mapper;

import com.dzc.springbootsecurity.entity.Permission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PermissionMapper extends Mapper<Permission> {
    List<Permission> findByUserId(int userId);
}