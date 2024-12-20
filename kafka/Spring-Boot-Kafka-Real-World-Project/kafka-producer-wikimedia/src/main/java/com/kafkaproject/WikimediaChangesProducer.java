package com.kafkaproject;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_recentchange";
        // inorder to read time wikimedia stream data we are going to use event source
        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate,topic);
        String uri = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder =  new EventSource.Builder(eventHandler, URI.create(uri));
        EventSource eventSource =  builder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);

    }
}
