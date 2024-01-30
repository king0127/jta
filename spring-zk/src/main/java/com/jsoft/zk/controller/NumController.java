package com.jsoft.zk.controller;

import com.jsoft.zk.domain.TenantEnum;
import com.jsoft.zk.utils.ZkUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;


// 编号测试zk锁

/**
 * @RefreshScope 刷新动态配置， 且
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RefreshScope
public class NumController {

    @Value("${change.switch.type}")
    private String switchType;

    @Value("#{${tenant.map:{\"PPVS\":\"https://vrdos-ppvs-api.dev.k8s.lixiang.com\",\"wms\":\"https://vrdos-wms-api.dev.k8s.lixiang.com\"}}}")
    private Map<String, String> tenantMap;

    @Value("${spring.datasource.name}")
    private String name;



    @GetMapping("/zk/lock")
    public String lockTest() {

        log.info(" spring.datasource.name：{} ", name);
        log.info(" change.switch.type：{} ", switchType);

        log.info(" 获取tenantMap：{} ", tenantMap);

        String domainName = null;

        String tenant = "PPVS";

        if(!tenantMap.isEmpty()) {
            for (Map.Entry<String, String> entry : tenantMap.entrySet()) {
                if(Objects.equals(tenant, entry.getKey())) {
                    domainName = entry.getValue();
                }
            }
        }
        System.out.println(domainName);

        try {
            if(ZkUtil.getLock(10)) {
                log.info(" 加锁 ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ZkUtil.release();
        }
        return "zk锁";
    }



}
