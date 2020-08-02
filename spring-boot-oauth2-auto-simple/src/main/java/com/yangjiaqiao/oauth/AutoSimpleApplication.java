package com.yangjiaqiao.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author 杨佳桥
 * @date 2020年07月31日 21:19
 * @description
 */
@SpringBootApplication
@EnableAuthorizationServer //开启授权服务器
@EnableResourceServer //开启资源服务
public class AutoSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoSimpleApplication.class,args);
    }
}
