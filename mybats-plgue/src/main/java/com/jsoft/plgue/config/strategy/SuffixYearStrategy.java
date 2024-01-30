package com.jsoft.plgue.config.strategy;

import com.jsoft.plgue.enums.SubTypeEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SuffixYearStrategy implements ITableNameStrategy, InitializingBean {

    @Override
    public String tableName(String baseTableName, Object meetValue) {
        return baseTableName + "_" + meetValue;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StrategyFactory.register(SubTypeEnums.SUFFIX_YEAR, this);
    }
}
