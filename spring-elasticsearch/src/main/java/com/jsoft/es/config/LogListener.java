package com.jsoft.es.config;

import com.jsoft.es.domain.UserPO;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.data.mongodb.core.messaging.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author YellowTail
 * @since 2020-06-02
 */
@Service
public class LogListener implements MessageListener<ChangeStreamDocument<Document>, UserPO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogListener.class);

    /**
     * 收到消息的时候，这个方法会被调用
     * @author YellowTail
     * @since 2020-06-03
     */
    public void onMessage(Message<ChangeStreamDocument<Document>, UserPO> message) {
        LOGGER.info("LogListener receive a message:{} ", message);
        String token = message.getRaw().getResumeToken().get("_data").asString().getValue();
        UserPO document = message.getBody();
        String _id = document.getMemberId();
    }
}

