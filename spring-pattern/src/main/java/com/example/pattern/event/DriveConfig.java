package com.example.pattern.event;

import com.example.pattern.event.domain.ResultPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DriveConfig {


    public void createResult(ResultPO resultPO) {

        log.info(" 获取创建评审的入参:{} ", resultPO);

    }

}
