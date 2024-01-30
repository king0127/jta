package com.jsoft.plgue.annotation;


import com.jsoft.plgue.enums.SubTypeEnums;
import com.jsoft.plgue.config.strategy.ITableNameStrategy;
import com.jsoft.plgue.config.strategy.TableNameStrategyVoid;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableCreate {

    /**
     * 启用自动建表,当表不存在的时候,是否创建表
     */
    boolean enableAutoCreateTable() default true;

    /**
     * 表名
     * @return
     */
    String tableName() default "";


    SubTypeEnums strategyName() default SubTypeEnums.SUFFIX;

    /**
     * 策略
     *
     * @return
     */
    Class<TableNameStrategyVoid> meetingStrategy() default TableNameStrategyVoid.class;

    /**
     * 表名策略，通过某种规则得到表名
     */
    Class<? extends ITableNameStrategy> strategy() default TableNameStrategyVoid.class;

}
