package com.kafkaproject;

import com.kafkaproject.entities.WikimediaData;
import com.kafkaproject.repository.WikimediaJpaRepository;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {


    private WikimediaJpaRepository wikimediaJpaRepository;

    public KafkaDatabaseConsumer(WikimediaJpaRepository wikimediaJpaRepository) {
        this.wikimediaJpaRepository = wikimediaJpaRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    @KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
    public void consume(String eventMessage)
    {
        logger.info(String.format("Event message received ->%s",eventMessage));
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        wikimediaJpaRepository.save(wikimediaData);

    }
}
