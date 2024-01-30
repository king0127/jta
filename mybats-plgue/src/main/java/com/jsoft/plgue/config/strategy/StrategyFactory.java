package com.jsoft.plgue.config.strategy;

import com.jsoft.plgue.enums.SubTypeEnums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StrategyFactory {

    private static final Map<String, ITableNameStrategy> serviceMap = new ConcurrentHashMap<>();

    // 将所有的策略注册到map中
    public static void register(SubTypeEnums typeEnums, ITableNameStrategy service){
        serviceMap.put(typeEnums.getType(), service);
    }

    public static String getService(String tableName, Object meetingVal, String type){
        ITableNameStrategy tableNameStrategy = serviceMap.get(type);
        if(tableNameStrategy != null){
            return tableNameStrategy.tableName(tableName, meetingVal);
        }
        // 如果为null 就给一个默认的策略模式
        return null;
    }

}
