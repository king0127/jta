package com.jsoft.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {


    /**
     * ServerEndpointExporter类作用是，扫描所有的服务器端点
     * 把带有@ServerEndpoint注解的所有类都添加进来
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }



}
