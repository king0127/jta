package com.example.pattern.decorator.mananger;

import com.example.pattern.decorator.file.Decorate;
import com.example.pattern.decorator.mananger.constant.ConstantCommon;
import com.example.pattern.decorator.mananger.domain.CertifcatePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Decorate(scene = ConstantCommon.TASK_TYPE, type = ConstantCommon.ESO)
public class ESOProductServiceDecorate extends AbstractHandler<String, Integer> {
    @Override
    public Integer handle(String type) {
        log.info(" 【ESO】 装饰器模式，在执行公共方法之前， 执行一些校验数据类型 ");
        CertifcatePO data = makeData();

        baseHandler.handle(data);

        log.info(" 【ESO】 执行公共方法之后， 处理一些额外其他操作---------- ");

        return 1;
    }

    public CertifcatePO makeData() {
        CertifcatePO po = CertifcatePO.builder()
                .id("id")
                .name("name")
                .build();
        return po;
    }

}
