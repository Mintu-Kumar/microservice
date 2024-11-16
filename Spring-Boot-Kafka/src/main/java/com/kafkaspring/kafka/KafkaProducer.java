package com.kafkaspring.kafka;


import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    protected  static  final Logger logger  = LoggerFactory.getLogger(KafkaProducer.class);
    // in order to send the message to the topic we are going to use spring provided kafka template
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // this is the method to send the message to the kafka topic
    public void  sendMessage(String message)
    {
        logger.info(String.format("Message sent %s",message));
        kafkaTemplate.send("topicguides",message);

    }
}
