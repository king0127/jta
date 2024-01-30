package com.jsoft.zk.domain;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TenantEnumUtil implements InitializingBean {

    @Value("#{${tenant.map:{\"PPVS\":\"https://vrdos-ppvs-api.dev.k8s.lixiang.com\",\"wms\":\"https://vrdos-wms-api.dev.k8s.lixiang.com\"}}}")
    private Map<String, String> tenantMap;

    @Override
    public void afterPropertiesSet() throws Exception {


        if(!tenantMap.isEmpty()) {

            TenantEnum[] values = TenantEnum.values();
            

        }


    }
}
