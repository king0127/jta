package com.example.pattern.decorator.mananger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class config {


    /**
     * bean注册， 扫描继承AbstractHandler类，注册到map中
     */
    @Bean
    public DecoratorManager handleDecorate(List<AbstractHandler> handlers) {
        DecoratorManager manager = new DecoratorManager();
        manager.setDecorateHandler(handlers);
        return manager;
    }

}
