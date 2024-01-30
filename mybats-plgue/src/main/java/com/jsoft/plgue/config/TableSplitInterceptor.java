package com.jsoft.plgue.config;

import com.jsoft.plgue.annotation.TableCreate;
import com.jsoft.plgue.enums.SubTypeEnums;
import com.jsoft.plgue.config.strategy.StrategyFactory;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * mybatis拦截器
 */
//@Component
//@Intercepts({
//        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
//})
@Slf4j
public class TableSplitInterceptor implements Interceptor {

    final static String LOGIC_DEL_KEY = "IS_DEL = 0";


    //匹配增删改查sql
    //  [\s] 空白
    //  {1,} 匹配最少一个
    //  \w 匹配字母、数字、下划线 `
    private Pattern pattern = Pattern.compile("(from|into|update)[\\s]{1,}(\\w{1,})", Pattern.CASE_INSENSITIVE);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
        String id = mappedStatement.getId();
        log.info("拦截到当前请求方法的全路径名为--->:  " + id);
        //sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();

        // 获取入参的结果集合, 并解析参数信息
//        ParameterMap parameterMap = mappedStatement.getParameterMap();
//        if(!parameterMap.getParameterMappings().isEmpty()) {
//            List<ParameterMapping> parameterMappings = parameterMap.getParameterMappings();
//            for (ParameterMapping parameterMapping : parameterMappings) {
//                ParameterMapping parameterMapping1 = parameterMappings.get(0);
//            }
//        }

        log.info(" 拦截当前的SQL方式：{} ", sqlCommandType);

        //获取到原始sql语句
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        String sql = boundSql.getSql();
        log.info(" -------执行的sql---------:{} ", sql);

        Object parameterObject = boundSql.getParameterObject();

        String val = "id";
        Object meetingId=null;

        /**
         * id： 处理分表
         * @Param, map, 实体类 ： 这种都是数据单条数据操作
         *
         * 多条数据怎么搞？？
         * select * from table where id in (); 这种集合查询
         * 获取入参数据，根据分表参数获取新的表将表分类， 如果单个就执行sql: 如果多个就 在 sql 后 拼接 union all, 参数不处理【参数通过预编译的？占位符处理的， 好像无法分组处理】？？
         *
         * 批量处理：insert 到不同表中需要获取不同表中的参数： 自己开启sqlSession进程进行编写sql执行，Map<tableName. 参数> 动态拼接sql执行处理
          */
        if(parameterObject != null) {
            if(parameterObject instanceof MapperMethod.ParamMap) {
                MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) parameterObject;
                if (!paramMap.containsKey(val)) {
                    log.error(" 分表策略字段必填请核对：{} ", val);
                }
                System.out.println(paramMap.get(val));
                meetingId = paramMap.get(val);
            } else {
                // 单参数形式:基础数据类型, map， 对象实体类
                if(parameterObject instanceof String || parameterObject instanceof Integer) {
                    log.error(" 分表策略字段不可为基础数据类型，或者请使用@Param注解：{} ", val);
                    log.info(" ==========基础数据类型======：{} ", parameterObject);
                } else if(parameterObject instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>) parameterObject;
                    if(!map.containsKey(val)) {
                        log.error(" 分表策略字段必填请核对：{} ", val);
                    }
                    meetingId = map.get(val);
                } else {
                    Class<?> aClass = parameterObject.getClass();
                    Field declaredField = aClass.getDeclaredField(val);
                    declaredField.setAccessible(true);
                    Object o = declaredField.get(parameterObject);
                    log.info(" 获取分表参数：{} ", o);
                    meetingId = o;
                }
            }
        }


        log.info(" 获取参数入参：{} ", parameterObject);

        /**
         * 第一种： 正则匹配数据表；
         * 第二种: 解析sql 获取 from表名称信息并替换
         * 第三种: 不通过拦截器处理，直接获取动态表名传递参数
         */
        //匹配sql
        Matcher matcher = pattern.matcher(sql);
        String tableName2 = null;
        if (matcher.find()) {
            tableName2 = matcher.group().trim();
        }

        String mSql = null;
        String className = id.substring(0, id.lastIndexOf("."));
        Class<?> aClass = Class.forName(className);
        if(aClass.isAnnotationPresent(TableCreate.class)) {
            TableCreate annotation = aClass.getAnnotation(TableCreate.class);
//            String handleSql = handleSql(sql);
//            log.info(" ---------------获取解析后的handleSql-------------:{} ", handleSql);
            String tableName = annotation.tableName();
            SubTypeEnums subTypeEnums = annotation.strategyName();
            String strategyName = StrategyFactory.getService(tableName, meetingId, subTypeEnums.getType());
            // 这种替换会有问题： select order_name from order where
//            mSql = sql.replaceAll(tableName, tableName.concat("_").concat(strategyName));
            mSql = sql.replaceAll(tableName, strategyName);
            log.info(" -------------分表处理后sql---------:{}  ", mSql);
        }
        // 将修改后的sql 设置回去
        metaObject.setValue("delegate.boundSql.sql", mSql);
        return invocation.proceed();
    }


    // 处理sql
    private String handleSql(String sql) throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse(sql);
        SelectBody selectBody = select.getSelectBody();
        if(selectBody instanceof PlainSelect) {
            setWhere((PlainSelect) selectBody);
        } else if (selectBody instanceof SetOperationList) {
            SetOperationList setOperationList = (SetOperationList) selectBody;
            List<SelectBody> selects = setOperationList.getSelects();
            selects.forEach(s -> {
                try {
                    setWhere((PlainSelect) s);
                } catch (JSQLParserException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return select.toString();
    }

    // 设置sql where条件
    private void setWhere(PlainSelect plainSelect) throws JSQLParserException {
        Table fromItem = (Table) plainSelect.getFromItem();
        // 别名， 表名
        Alias alias = fromItem.getAlias();
        String name = fromItem.getName();
        String asTable = alias == null ? name : alias.getName(); // 有别名用别名，无别名用表名
        String _filter = asTable+"."+LOGIC_DEL_KEY;

        Expression where = plainSelect.getWhere();
        // 判断是否有where条件
        if(null == where) {
            plainSelect.setWhere(CCJSqlParserUtil.parseCondExpression(_filter));
        } else {
            plainSelect.setWhere(new AndExpression(plainSelect.getWhere(), CCJSqlParserUtil.parseCondExpression(_filter)));
        }

        List<Join> joins = plainSelect.getJoins();
        if(!CollectionUtils.isEmpty(joins)) {
            for (Join join : joins) {
                FromItem rightItem = join.getRightItem();
                Alias joinAlias = rightItem.getAlias();
                String joinDel = joinAlias+"."+LOGIC_DEL_KEY;
                plainSelect.setWhere(new AndExpression(plainSelect.getWhere(), CCJSqlParserUtil.parseCondExpression(joinDel)));
                System.out.println("右侧链接表别名"+rightItem.getAlias());
            }
        }

    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
