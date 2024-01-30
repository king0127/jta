package com.jsoft.plgue.config.shard;

public interface ShardTableStrategy {

    String getTableName(String tableName, String shardFlag, int num);

}
