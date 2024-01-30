package com.jsoft.plgue.config.shard;

import com.jsoft.plgue.enums.ShardStrategyEnums;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public abstract class ShardTableStrategyFactory {


    private static final Map<String, ShardTableStrategy> serviceMap = new ConcurrentHashMap<>();

    public static void register(ShardStrategyEnums typeEnums, ShardTableStrategy service){
        serviceMap.put(typeEnums.name(), service);
    }

    public static String getService(String tableName, String shardFlag, String type, int num) {
        log.info(" ShardTableStrategyFactory#getService(), params: ---tableName:{}--, ---shardFlag:{}--, ---type:{}--, ---num:{}-- ",
                tableName, shardFlag, type, num);
        ShardTableStrategy tableNameStrategy = serviceMap.get(type);
        if(tableNameStrategy == null) {
//            throw new ShardErrorException(StatusCode.SHARD_NOT_STRATEGY);
        }
        return tableNameStrategy.getTableName(tableName, shardFlag, num);
    }

}
