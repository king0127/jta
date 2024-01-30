package com.jsoft.plgue;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.jsoft.plgue.mapper")
public class MybatsPlgueApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatsPlgueApplication.class, args);
    }

}
