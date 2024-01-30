package com.jsoft.rocket.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoft.rocket.constant.MqTopic;
import com.jsoft.rocket.po.Base64File;
import com.jsoft.rocket.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "my-group123", topic = MqTopic.FILE_TOPIC, messageModel = MessageModel.BROADCASTING)
public class FileConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String msg) {

        log.info(" 接收到消息-{}，消息接受时间 {} ", msg, System.currentTimeMillis());
        ObjectMapper mapper = new ObjectMapper();
        try {
            Base64File beanFile = mapper.readValue(msg, Base64File.class);
            log.info(" 消费的消息 {} ", beanFile);
            File file = FileUtil.base64Tofile(beanFile.getFileContent(), "D:\\temp", beanFile.getFileName());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
