package com.kafkaspring.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static  final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    //within this class we are going to create subscriber method which will subscribe to the topic

    // to this weill use the kafkalistner annotation from kafka library to listen or subscribe to the topic

    @KafkaListener(topics = "topicguides", groupId = "myGroup")
    public void  consume(String message)
    {
        logger.info(String.format("Message received -> %s",message));
    }
}
