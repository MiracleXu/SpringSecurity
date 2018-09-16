package com.dzc.springbootsecurity.conf;

import com.dzc.springbootsecurity.service.CustomUserService;
import com.dzc.springbootsecurity.service.SimpleFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private SimpleFilterSecurityInterceptor simpleFilterSecurityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 定制请求授权规则
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/level1/**").hasRole("VIP1")
//                .antMatchers("/level2/**").hasRole("VIP2")
//                .antMatchers("/level3/**").hasRole("VIP3");
//
//        // 开启自动配置的登录功能
//        http.formLogin().loginPage("/userlogin");
//        http.logout().logoutSuccessUrl("/");

//        上面配置都是写死的配置，
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/userlogin").permitAll()
                .and()
                .logout().logoutSuccessUrl("/");

        http.addFilterBefore(simpleFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        基于内存的配置验证方式
//        auth.inMemoryAuthentication()
//                .withUser("dzc").password("123").roles("VIP1", "VIP2")
//                .and()
//                .withUser("zyw").password("123").roles("VIP2", "VIP3");

        // 用户自定义扩展验证
        auth.userDetailsService(customUserService);
    }
}
