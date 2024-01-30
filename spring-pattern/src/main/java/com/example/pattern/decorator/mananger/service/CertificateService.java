package com.example.pattern.decorator.mananger.service;

import com.example.pattern.decorator.mananger.BaseHandler;
import com.example.pattern.decorator.mananger.domain.CertifcatePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CertificateService implements BaseHandler<CertifcatePO, Integer> {
    @Override
    public Integer handle(CertifcatePO certifcatePO) {
        // 1、证书数据保存
        // 2、代理人信息保存
        // 3、相关流水数据保存
        // 其他的一些列核心操作
        log.info("  ===================================================  ");
        log.info(" 执行完成的公共核心逻辑 ");
        log.info("  ===================================================  ");
        Integer agentId = Integer.MAX_VALUE;
        // 返回代理人id
        return agentId;
    }
}
