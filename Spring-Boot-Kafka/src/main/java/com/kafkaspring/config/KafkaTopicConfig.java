package com.kafkaspring.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    // here we will create a bean to create a kafka topic

    @Bean
    public NewTopic createTopic()
    {
        return TopicBuilder.name("topicguides")   // here we can specify the partition how many partition we want to of our topic
                .build();
    }

    @Bean
    public NewTopic createJsonTopic()
    {
        return TopicBuilder.name("topicguides_json")   // here we can specify the partition how many partition we want to of our topic
                .build();
    }
}
