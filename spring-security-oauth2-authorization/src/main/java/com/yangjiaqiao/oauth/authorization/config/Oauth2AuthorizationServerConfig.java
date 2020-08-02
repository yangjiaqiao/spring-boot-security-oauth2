package com.yangjiaqiao.oauth.authorization.config;

import com.yangjiaqiao.oauth.authorization.properties.ClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author 杨佳桥
 * @date 2020年08月01日 22:32
 * @description
 */
@Configuration //配置文件
@RequiredArgsConstructor //减少依赖注入带来的代码冗余
@EnableAuthorizationServer //开启授权服务器
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private ClientProperties clientProperties;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
//
//        builder
//                //构建一个ID为oauth2的客户端
//                .withClient("oauth2")
//                //设置客户端的密钥,需要进行密钥的加密
//                .secret(passwordEncoder.encode("oauth2"))
//                .resourceIds("oauth2")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .authorities("ROLE_ADMIN","ROLE_USER")
//                .scopes("all")
//                .accessTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
//                // 刷新 token 的有效期
//                .refreshTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
//                // 授权码模式的重定向地址
//                .redirectUris("http://example.com");
        configClient(clients);
    }

    private void configClient(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        for (BaseClientDetails client : clientProperties.getClient()) {
            ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder clientBuilder =
                    builder.withClient(client.getClientId());
            clientBuilder
                    .secret(client.getClientSecret())
                    .resourceIds(client.getResourceIds().toArray(new String[0]))
                    .authorizedGrantTypes(client.getAuthorizedGrantTypes().toArray(new String[0]))
                    .authorities(
                            AuthorityUtils.authorityListToSet(client.getAuthorities())
                                    .toArray(new String[0]))
                    .scopes(client.getScope().toArray(new String[0]));
            if (client.getAutoApproveScopes() != null) {
                clientBuilder.autoApprove(
                        client.getAutoApproveScopes().toArray(new String[0]));
            }
            if (client.getAccessTokenValiditySeconds() != null) {
                clientBuilder.accessTokenValiditySeconds(
                        client.getAccessTokenValiditySeconds());
            }
            if (client.getRefreshTokenValiditySeconds() != null) {
                clientBuilder.refreshTokenValiditySeconds(
                        client.getRefreshTokenValiditySeconds());
            }
            if (client.getRegisteredRedirectUri() != null) {
                clientBuilder.redirectUris(
                        client.getRegisteredRedirectUri().toArray(new String[0]));
            }
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
    }

}
