package com.jsoft.rocket.controller;

import com.jsoft.rocket.transaction.service.ShopService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/shop")
public class ShopController {


    @Resource
    ShopService shopService;

    @GetMapping("/doShop")
    public String doShop() throws Exception {
        shopService.doShop();
        return "成功";
    }

}
