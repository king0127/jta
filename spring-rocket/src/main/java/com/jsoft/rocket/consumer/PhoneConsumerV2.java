package com.jsoft.rocket.consumer;

import com.jsoft.rocket.constant.MqTopic;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "my-group1", topic = MqTopic.TEST_TOPIC, selectorExpression = "tag1 || tag2", selectorType = SelectorType.TAG)
public class PhoneConsumerV2 implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info(" 版本V2,消费的消息 {} ", s);

        try {
            throw new Exception("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
