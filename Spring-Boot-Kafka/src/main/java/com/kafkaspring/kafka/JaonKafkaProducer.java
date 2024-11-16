package com.kafkaspring.kafka;

import com.kafkaspring.payload.User;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JaonKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(JaonKafkaProducer.class);

    private KafkaTemplate<String, User> kafkaTemplate;

    public JaonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user){

        logger.info(String.format("Message sent ->%s",user.toString()));
        Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC,"topicguides_json")
                .build();
        kafkaTemplate.send(message);
    }
}
