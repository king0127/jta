package com.jsoft.plgue.config;

import com.jsoft.plgue.annotation.TableShard;
import com.jsoft.plgue.config.shard.ShardTableStrategyFactory;
import com.jsoft.plgue.enums.ShardStrategyEnums;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * 分表拦截器
 */
//@Component
//@Intercepts({
//        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
//})
@Slf4j
public class MybatisShardInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String path = mappedStatement.getId();
        String className = path.substring(0, path.lastIndexOf("."));
        Class<?> aClass = Class.forName(className);
        // 判断是否被注解修饰，如果被修饰则执行分表替换操作
        if(aClass.isAnnotationPresent(TableShard.class)) {
            log.info("拦截到当前请求方法的全路径名为--->{}  ", path);
            BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
            String sql = boundSql.getSql();
            TableShard tableShard = aClass.getAnnotation(TableShard.class);
            String tableName = tableShard.tableName();
            ShardStrategyEnums shardStrategyEnums = tableShard.shardStrategy();
            String shardFlag = tableShard.shardFlag();
            int num = tableShard.num();
            String shardVal = getShardFlagParam(boundSql.getParameterObject(), shardFlag);
            String strategyName = ShardTableStrategyFactory.getService(tableName, shardVal, shardStrategyEnums.getName(), num);
            assert strategyName != null;
            String mSql = sql.replaceAll(tableName, strategyName);
            log.info(" -------------分表处理后sql---------:{}  ", mSql);
            // 将修改后的sql 设置回去
            metaObject.setValue("delegate.boundSql.sql", mSql);
        }
        return invocation.proceed();
    }

    /**
     * 参数中获取分表的标识值
     */
    private String getShardFlagParam(Object parameterObject, String shardFlag)  {
        String shardVal = null;
        if(parameterObject != null) {
            if(parameterObject instanceof MapperMethod.ParamMap) {
                MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) parameterObject;
                if (!paramMap.containsKey(shardFlag)) {
                    log.error(" 分表策略字段必填请核对：{} ", shardFlag);
//                    throw new ShardErrorException(StatusCode.SHARD_NOT_FOUND);
                }
                shardVal = String.valueOf(paramMap.get(shardFlag));
            } else {
                // 单参数形式:基础数据类型, map， 对象实体类
                if(parameterObject instanceof String || parameterObject instanceof Integer) {
                    log.error(" 分表策略字段不可为基础数据类型，或者请使用@Param注解：{} ", shardFlag);
//                    throw new ShardErrorException(StatusCode.SHARD_NOT_SUPPORTED);
                } else if(parameterObject instanceof Map) {
                    Map<String, String> map = (Map<String, String>) parameterObject;
                    if(!map.containsKey(shardFlag)) {
                        log.error(" 分表策略字段必填请核对：{} ", shardFlag);
//                        throw new ShardErrorException(StatusCode.SHARD_NOT_FOUND);
                    }
                    shardVal = map.get(shardFlag);
                } else {
                    try {
                        Class<?> aClass = parameterObject.getClass();
                        Field declaredField = aClass.getDeclaredField(shardFlag);
                        declaredField.setAccessible(true);
                        Object obj = declaredField.get(parameterObject);
                        log.info(" 获取分表参数：{} ", obj);
                        shardVal = String.valueOf(obj);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        log.error(" 参数解析失败：{} ", e.getMessage());
//                        throw new ShardErrorException(StatusCode.SHARD_NOT_FOUND);
                    }
                }
            }
        }
        return shardVal;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
