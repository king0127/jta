package com.example.redis.controller;

import com.example.redis.service.OrderService;
import com.google.common.hash.BloomFilter;
import com.mysql.cj.log.Log;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/order/")
public class OrderController {


    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private OrderService orderService;

    @GetMapping("/save")
    public String save() {

        orderService.save();
        return null;
    }



    @PostMapping("/list")
    public String getList() {

        System.out.println(" ============>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<=================== ");
        return "测试数据";
    }


    public static final String SOR_PRE = "SOR:SCHEDULE:";

    @GetMapping("/redis/test")
    public void test() {

        System.out.println(" ===========================开始删除key================ ");
        redisTemplate.delete(SOR_PRE+"SOR-109");
        System.out.println(" ===========================结束删除key================ ");


        for (int i = 0; i < 20; i++) {
            redisTemplate.opsForValue().set(SOR_PRE+"SOR-00" + String.valueOf(i), "测试数据", 60, TimeUnit.SECONDS);
            if(i == 10) {
                redisTemplate.delete(SOR_PRE+"SOR-009");
            }
        }

//        List<String> list = new ArrayList<>();
//        list.add("SOR-001");
//        list.add("SOR-002");
//        list.add("SOR-003");
//        list.add("SOR-004");
//        list.add("SOR-005");
//        list.add("SOR-006");
//        list.add("SOR-007");
//        list.add("SOR-008");
//        redisTemplate.opsForList().leftPushAll("sor-key", list);
//        redisTemplate.expire("sor-key", 10, TimeUnit.SECONDS);
//        redisTemplate.opsForValue().set("notify-task-001", "测试数据", 10, TimeUnit.SECONDS);
    }

}
