package com.jsoft.rocket.transaction.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoft.rocket.po.vo.StockShopVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RocketMQTransactionListener
public class ShopListener implements RocketMQLocalTransactionListener {

    private final ConcurrentHashMap<String, Boolean> TRANSACTION_MAP = new ConcurrentHashMap();

    private final String TAG_SCORE = "SHOP_V1";

    public RocketMQLocalTransactionState sendMsg(Message msg, Object o) {
        String json = new String((byte[])msg.getPayload());
        log.info(" 解析消息结构体 json = " + json);

        MessageHeaders messageHeaders = msg.getHeaders();
        if(messageHeaders != null) {
            String tag = messageHeaders.get(RocketMQHeaders.TOPIC, String.class);
            log.info("tag = " + tag);
            log.info("TAG_SCORE = " + TAG_SCORE);

            if(TAG_SCORE.equals(tag)) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    StockShopVO stockShopVO = objectMapper.readValue(json, StockShopVO.class);
                    Integer id = stockShopVO.getId();
                    log.info("id = " + id);
                    //需要进行幂等校验（因为RocketMq会不断回查，即多次回查）
                    if(id == 1) {
                        return RocketMQLocalTransactionState.COMMIT;
                    }else {
                        return RocketMQLocalTransactionState.ROLLBACK;
                    }
                } catch (JsonProcessingException e) {
                    log.error(" Json process error ：{} ", e.getMessage());
                }
            }
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        return sendMsg(message, o);
//        log.info(" message: {}, o: {} ", message, o);
//        String transactionId = message.getHeaders().get("rocketmq_TRANSACTION_ID", String.class);
//        if(Objects.equals(o, "tag")) {
//            TRANSACTION_MAP.put(transactionId, Boolean.TRUE);
//            return RocketMQLocalTransactionState.COMMIT;
//        } else {
//            TRANSACTION_MAP.put(transactionId, Boolean.FALSE);
//            return RocketMQLocalTransactionState.ROLLBACK;
//        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {


        return sendMsg(message, null);
//        log.info(" message: {}", message);
//
//        String transactionId = message.getHeaders().get("rocketmq_TRANSACTION_ID", String.class);
//        if(TRANSACTION_MAP.get(transactionId)) {
//            return RocketMQLocalTransactionState.COMMIT;
//        } else {
//            return RocketMQLocalTransactionState.ROLLBACK;
//        }
    }
}
