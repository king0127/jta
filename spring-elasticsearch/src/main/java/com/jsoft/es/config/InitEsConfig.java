package com.jsoft.es.config;

import com.jsoft.es.domain.UserPO;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.messaging.ChangeStreamRequest;
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 初始化ES配置：创建索引
 * 整体思路：项目启动时候检测该索引是否存在，如果不存在创建索引
 *
 * 数据流： MQ ---> kafka ---> flume ---> hdfs ---> hive ::: spark计算 ---> PG/ES/CK
 */
@Configuration
public class InitEsConfig {

    // 配置mongodb变更流
    /*@Resource
    MongoTemplate template;
    @Resource
    LogListener logListener;

    @EventListener(ApplicationStartedEvent.class)
    public void subscript() {
        ChangeStreamRequest<UserPO> request = ChangeStreamRequest.builder(logListener)
                .collation(Collation.parse("userPO"))
                .filter(Aggregation.newAggregation(Aggregation.match(Criteria.where("operationType")
                        .in("insert", "update", "replace"))))
                .build();
        messageListenerContainer().register(request, UserPO.class);
    }

    @Bean
    MessageListenerContainer messageListenerContainer() {
        Executor executor = Executors.newSingleThreadExecutor();
        return new DefaultMessageListenerContainer(template, executor) {
            @Override
            public boolean isAutoStartup() {
                return true;
            }
        };
    }*/





}
