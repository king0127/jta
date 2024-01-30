package com.jsoft.rocket.controller;

import com.jsoft.rocket.constant.MqTopic;
import com.jsoft.rocket.po.Base64File;
import com.jsoft.rocket.utils.FileUtil;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    public void sendFile() {
        String fileBase64 = FileUtil.fileBase64("D:/workspance/pers/spring-jta/spring-rocket/src/main/resources/评审二期任务.xlsx");

        Base64File file = new Base64File();
        file.setFileContent(fileBase64);
        file.setFileName("评审二期任务.xlsx");
        rocketMQTemplate.syncSend(MqTopic.FILE_TOPIC, MessageBuilder.withPayload(file).build());
        System.out.println(" 消息发送成功 ");
    }




}
