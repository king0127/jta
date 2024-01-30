package com.jsoft.rocket.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "my-group999", topic = "DELAY_TOPIC", messageModel = MessageModel.BROADCASTING)
public class DelayConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String msg) {


        log.info(" 接收到消息-{}，消息接受时间 {} ", msg, System.currentTimeMillis());

    }
}
