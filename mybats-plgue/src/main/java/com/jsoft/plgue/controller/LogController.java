package com.jsoft.plgue.controller;

import com.jsoft.plgue.annotation.LogAnnotation;
import com.jsoft.plgue.domain.resp.NumPO;
import com.jsoft.plgue.enums.CommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/log")
public class LogController {

    @Value("#{${tenant.url}}")
    private Map<String, String> tenantUrlMap;

    @LogAnnotation(name = "日志管理")
    @GetMapping("/test")
    public String printLog() {

        System.out.println(tenantUrlMap);

        System.out.println(CommonEnum.initList());
        Set<String> strings = CommonEnum.initList();
        System.out.println(strings.contains("WMS"));
        return "成功";
    }


    public static void main(String[] args) {
        NumPO po = NumPO.builder().id(1).num(1).is_del(0).key("测试").build();
        NumPO po2 = NumPO.builder().id(1).num(2).is_del(0).key("订单").build();
        List<NumPO> list = new ArrayList<>();
        list.add(po);
        list.add(po2);
        Map<String, Integer> keyMap = list.stream().collect(Collectors.toMap(NumPO::getKey, NumPO::getNum));
        log.info(" 数据:{} ", keyMap);
    }


}
