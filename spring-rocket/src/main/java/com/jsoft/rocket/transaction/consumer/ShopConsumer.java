package com.jsoft.rocket.transaction.consumer;

import com.jsoft.rocket.constant.MqTopic;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@RocketMQMessageListener(consumerGroup = "my-group1", topic = MqTopic.TEST_TOPIC, selectorExpression = "tag1 || tag2", selectorType = SelectorType.TAG, messageModel = MessageModel.BROADCASTING)
@RocketMQMessageListener(consumerGroup = "my-group2", topic = MqTopic.SHOP_TOPIC, messageModel = MessageModel.BROADCASTING)
public class ShopConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {

        log.info(" 消费消息：{} ", s);
        int a = 1/0;
    }
}
