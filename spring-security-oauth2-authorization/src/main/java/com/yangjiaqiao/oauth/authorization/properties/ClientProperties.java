package com.yangjiaqiao.oauth.authorization.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.List;

/**
 * @author 杨佳桥
 * @date 2020年08月01日 23:15
 * @description
 */
@Data
@Configuration
@ConfigurationProperties("application.security.oauth")
public class ClientProperties {
    /** 客户端集合 */
    private List<BaseClientDetails> client;
}
