package com.jsoft.plgue.config.strategy;

import com.jsoft.plgue.enums.SubTypeEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SuffixMeetStrategy implements ITableNameStrategy, InitializingBean {

    private Integer SUFFIX_LENGTH = 1;

    @Override
    public String tableName(String baseTableName, Object meetValue) {
        Map<String, String> map = typeChange(meetValue);
//        return baseTableName + "_" +meetValue.substring(0, SUFFIX_LENGTH);
        return baseTableName + "_" + 1;
    }

    // 批量查询的数据类型： list= 批量查询, List<Bean> 批量新增
    private Map<String, String> typeChange(Object obj) {

        if(obj instanceof List) {
            List<String> list = (List<String>) obj;
            log.info(" list类型参数:{} ", list);
        } else {
            log.info(" 获取单参数：{} ", obj);
        }

        return null;
    }

    @Override
    public void afterPropertiesSet() {
        StrategyFactory.register(SubTypeEnums.SUFFIX, this);
    }
}
