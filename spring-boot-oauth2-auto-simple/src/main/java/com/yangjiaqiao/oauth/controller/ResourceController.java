package com.yangjiaqiao.oauth.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author 杨佳桥
 * @date 2020年07月31日 21:24
 * @description 资源测试
 */
@RestController
public class ResourceController {

    /**
     * @param principal 当前登录用户
     * @author 杨佳桥
     * @date 2020-07-31 21:26
     * @description: 获取当权登录用户的信息
     */
    @GetMapping("/resource")
    public HttpEntity<?> response(Principal principal){
        return ResponseEntity.ok(principal);
    }
}
