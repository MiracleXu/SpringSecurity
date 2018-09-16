package com.dzc.springbootsecurity.service;

import com.dzc.springbootsecurity.entity.Permission;
import com.dzc.springbootsecurity.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-09-16
 * Time: 10:42
 */
@Service
public class SimpleInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {


    @Autowired
    private PermissionMapper permissionMapper;

    private HashMap<String, Collection<ConfigAttribute>> attributeMap;


    public void loadAttributeFromDatabase() {
        attributeMap = new HashMap<>();
        List<Permission> permissions = permissionMapper.selectAll();
        permissions.forEach(p -> {
            ConfigAttribute attribute = new SecurityConfig(p.getName());
            Collection<ConfigAttribute> c = new ArrayList<>();
            c.add(attribute);
            attributeMap.put(p.getUrl(), c);
        });
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (attributeMap == null)
            loadAttributeFromDatabase();
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        for (String url : attributeMap.keySet()) {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
            if (matcher.matches(request)) {
                return attributeMap.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
