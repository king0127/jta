package com.example.pattern.controller;

import com.example.pattern.domain.PartChangeReq;
import com.example.pattern.singleton.ThSingleton;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestCro {

    @PostMapping("/test")
    public void test(@RequestBody PartChangeReq param) {
        System.out.println(param);
        System.out.println(new ArrayList<String>());
        System.out.println(param.getIds().size());
    }


    @GetMapping("/main")
    public void testMain() {

        Map<String, Class<?>> map = new HashMap<>();
        map.put("meeting", MeetingPO.class);
        map.put("topic", TopicPO.class);
        map.put("a", List.class);
    }

    @GetMapping("/test1")
    public void test1() {
        ThSingleton thSingleton = ThSingleton.getThSingleton();
        thSingleton.setCode(1);
        thSingleton.setMsg("测试");

        System.out.println(thSingleton.hashCode());

    }

    @GetMapping("/test12")
    public void test12() {
        ThSingleton thSingleton = ThSingleton.getThSingleton();
        System.out.println(thSingleton.hashCode());
    }

    public MeetingPO meeting() {

        return new MeetingPO();
    }


    public TopicPO topic() {

        return new TopicPO();
    }

}
