package com.jsoft.rocket.controller;

import com.jsoft.rocket.constant.MqTopic;
import com.jsoft.rocket.po.OrderPO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {


    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send/delay")
    public void sendDelayMsg() {

        OrderPO po = new OrderPO();
        po.setName("king");
        po.setId(1);
        po.setIsDel(0);
        log.info(" 【发送消息时间】：{} ", System.currentTimeMillis());
        // 使用延迟队列实现 30min内未支付订单超时类型需求
        rocketMQTemplate.syncSend("DELAY_TOPIC", MessageBuilder.withPayload(po).build(), 1000, 5);
    }

//    @QuartzTransactionManager
    @GetMapping("/send")
    public void sendMsg()  {

        for (int i = 0; i < 4; i++) {
            String tag = "tag";
            OrderPO po = new OrderPO();
            po.setId(1);
//            po.setName("华为手机");
//            po.setOrderCode("HW-RY-V01");
//            po.setIsDel(0);
            tag = tag + i;
//            po.setTag(tag);
            log.info(" 消息生成的tag ----> {} ", tag);
            SendResult sendResult = rocketMQTemplate.syncSend(MqTopic.TEST_TOPIC + ":" + tag, MessageBuilder.withPayload(po).build());

            SendStatus sendStatus = sendResult.getSendStatus();

            log.info(" 打印发送消息的返回结果：{} ", sendResult);
        }

    }



    @GetMapping("/delay/msg")
    public void delaySendMsg() {

        List<OrderPO> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            OrderPO po = new OrderPO();
            po.setIsDel(0);
            po.setId(i);
            po.setName("理想-ONE");
//            po.setOrderCode("LX-ONE");
//            po.setTag("test");
            list.add(po);
        }
        log.info(" 消息发送时间 {} ", System.currentTimeMillis());
        // 异步发送延迟队列报错， 请把延迟时间调整为3s以上
        rocketMQTemplate.asyncSend(MqTopic.DELAY_TOPIC, MessageBuilder.withPayload(list).build(), callback(), 3000,3);

    }

    public SendCallback callback() {
        return new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                if (sendResult.getSendStatus().equals(SendStatus.SEND_OK)) {
                    MessageQueue messageQueue = sendResult.getMessageQueue();
                    String topic = messageQueue.getTopic();
                    String brokerName = messageQueue.getBrokerName();
                    int queueId = messageQueue.getQueueId();
                    log.info(" 消息投递成功，Topic: {}， BrokerName: {}, queueId: {} ", topic, brokerName, queueId);
                }
            }

            @Override
            public void onException(Throwable throwable) {
                log.info(" 消息投递失败 {}", throwable.getMessage());
            }
        };
    }
}

