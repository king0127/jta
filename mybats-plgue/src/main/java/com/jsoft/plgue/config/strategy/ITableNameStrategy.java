package com.jsoft.plgue.config.strategy;

public interface ITableNameStrategy {


    /**
     * @param baseTableName     基础表名
     * @param meetValue         会议val
     * @return
     */
    String tableName(String baseTableName, Object meetValue);

}
