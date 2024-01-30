package com.jsoft.plgue.config.shard;

import com.jsoft.plgue.enums.ShardStrategyEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LastShardTableStrategy implements ShardTableStrategy, InitializingBean {

    @Override
    public String getTableName(String tableName, String shardFlag, int num) {
        log.info(" 截取会议：{} 的最后 {} 位 ", shardFlag, num);
        Long meetingId = Long.valueOf(shardFlag);
        long pow = (long) Math.pow(10, num);
        return tableName + "_" + (meetingId % pow);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ShardTableStrategyFactory.register(ShardStrategyEnums.LAST_POSITION , this);
    }
}
