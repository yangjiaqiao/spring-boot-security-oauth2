package com.yangjiaqiao.oauth.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 杨佳桥
 * @date 2020年08月01日 22:40
 * @description 获取加密后的密码
 */
public class PasswordTest {
    @Test
    public void password() {
        // 每次打印的结果都不一样，不影响
        System.out.println(new BCryptPasswordEncoder().encode("oauth2"));
    }

}
