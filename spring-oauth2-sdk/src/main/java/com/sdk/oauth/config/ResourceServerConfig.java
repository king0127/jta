//package com.sdk.oauth.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    public ResourceServerConfig() {
//        super();
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId("rid") // 配置资源ID
//                .stateless(true); // 设置这些资源仅基于令牌认证
//
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable().authorizeRequests();
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .requestMatchers()
//                // /api/** 请求需要oauth鉴权
//                .antMatchers("/api/**")
//                .antMatchers("/oauth/**");
//        ;
//    }
//}
