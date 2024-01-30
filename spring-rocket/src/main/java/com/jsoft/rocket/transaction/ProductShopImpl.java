package com.jsoft.rocket.transaction;

import com.jsoft.rocket.constant.MqTopic;
import com.jsoft.rocket.mapper.ShopMapper;
import com.jsoft.rocket.po.OrderPO;
import com.jsoft.rocket.po.vo.StockShopVO;
import com.jsoft.rocket.transaction.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.schema.Table;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Slf4j
public class ProductShopImpl implements ShopService {

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Transactional
    @Override
    public void doShop() {
        // 下订单: 先扣库存， 然后先订单

        try {
            StockShopVO stockShopVO = StockShopVO.builder().id(1).num(2).name("火龙果").build();
            Message<StockShopVO> msgJson = MessageBuilder.withPayload(stockShopVO)
                    .setHeader(RocketMQHeaders.TOPIC, MqTopic.SHOP_TOPIC)
                    .setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID().toString())
                    .build();


            TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(null, MqTopic.SHOP_TOPIC, msgJson, "tag-shop");
            if(sendResult.getSendStatus() != SendStatus.SEND_OK) {
                throw new RuntimeException("MQ消息发送失败");
            }
            log.info(" 发送消息的返回的信息：{} ", sendResult);

            String msgId = sendResult.getMsgId();
            LocalTransactionState localTransactionState = sendResult.getLocalTransactionState();
            log.info(" 发送消息ID：{}， 执行消息的本地事务状态：{} ", msgId, localTransactionState);

            // 下订单： 保存
            OrderPO orderPO = OrderPO.builder()
                .name("黄瓜")
                .isDel(0)
                .build();
            shopMapper.insert(orderPO);
        } catch (Exception e) {
            log.error(" doShop get an exception: {} ", e.getMessage());
        }
    }
}
