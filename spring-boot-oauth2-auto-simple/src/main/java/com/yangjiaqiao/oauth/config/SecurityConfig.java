package com.yangjiaqiao.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author 杨佳桥
 * @date 2020年07月31日 21:36
 * @description
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @return PasswordEncoder
     * @author 杨佳桥
     * @date 2020-08-01 8:54
     * @description: 密码加密方式。Spring5 后必须对密码进行加密
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * @return InMemoryUserDetailsManager
     * @author 杨佳桥
     * @date 2020-08-01 8:58
     * @description:
     * 创建两个内存用户
     * 用户名：user 密码：123456 角色：ROLE_USER
     * 用户名：admin 密码：123456 角色：ROLE_ADMIN
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password(passwordEncoder().encode("123456")).authorities("ROLE_USER").build());
        manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("123456")).authorities("ROLE_ADMIN").build());
        return manager;
    }
}
