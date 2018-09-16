package com.dzc.springbootsecurity.service;

import com.dzc.springbootsecurity.entity.Permission;
import com.dzc.springbootsecurity.entity.User;
import com.dzc.springbootsecurity.mapper.PermissionMapper;
import com.dzc.springbootsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-09-15
 * Time: 12:50
 */
@Service
public class CustomUserService implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUserName(username);
        if (user != null) {
            // 直接从用户的角色关联出来
//            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            user.getRoles().forEach(role ->
//                    authorities.add(new SimpleGrantedAuthority(role.getName()))
//            );
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

            // 获取用户权限信息
            List<Permission> permissions = permissionMapper.findByUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            permissions.forEach(p -> grantedAuthorities.add(new SimpleGrantedAuthority(p.getName())));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }
}
