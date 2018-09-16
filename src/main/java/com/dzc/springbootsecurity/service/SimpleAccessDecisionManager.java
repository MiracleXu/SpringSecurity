package com.dzc.springbootsecurity.service;

import com.alibaba.druid.util.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-09-16
 * Time: 10:30
 */
@Service
public class SimpleAccessDecisionManager implements AccessDecisionManager {

    /**
     * 判定是否拥有权限的决策方法
     *
     * @param authentication   在CustomService中加入的权限集合
     * @param object           包含客户端发起的请求信息 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     * @param configAttributes 配置的权限信息结果集
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtils.isEmpty(configAttributes)) {
            return;
        }

        for (ConfigAttribute configAttribute : configAttributes) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (StringUtils.equals(authority.getAuthority(), configAttribute.getAttribute())) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
