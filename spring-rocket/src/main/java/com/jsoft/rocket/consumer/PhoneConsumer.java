package com.jsoft.rocket.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoft.rocket.constant.MqTopic;
import com.jsoft.rocket.po.OrderPO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * topic: 订阅的topic
 * selectorExpression： 设定需要过滤的消息 tag
 * selectorType: 消息过滤的方式
 * messageModel： 消息消费的模式； 默认模式--> 一个消息只能被一个消费这消息；  MessageModel.BROADCASTING --> 这个模式只要订阅topic的消费者够可以消费
 */
@Slf4j
@Component
//@RocketMQMessageListener(consumerGroup = "my-group1", topic = MqTopic.TEST_TOPIC, selectorExpression = "tag1 || tag2", selectorType = SelectorType.TAG, messageModel = MessageModel.BROADCASTING)
@RocketMQMessageListener(consumerGroup = "test", topic = MqTopic.DELAY_TOPIC, messageModel = MessageModel.BROADCASTING)
public class PhoneConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {

        log.info(" 接收到消息-{}，消息接受时间 {} ", s, System.currentTimeMillis());
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<OrderPO> po = mapper.readValue(s, new TypeReference<List<OrderPO>>() {});
            log.info(" 消费的消息 {} ", po);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
