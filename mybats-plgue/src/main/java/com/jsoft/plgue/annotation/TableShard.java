package com.jsoft.plgue.annotation;

import com.jsoft.plgue.enums.ShardStrategyEnums;

import java.lang.annotation.*;


@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableShard {

    /**
     * 需要分表的表名
     */
    String tableName() default "common_member";


    /**
     * 分表策略
     */
    ShardStrategyEnums shardStrategy() default ShardStrategyEnums.LAST_POSITION;

    /**
     * 取几位
     */
    int num() default 1;

    /**
     * 分表标识： 根据meetingId字段值分表
     */
    String shardFlag() default "meetingId";

}
