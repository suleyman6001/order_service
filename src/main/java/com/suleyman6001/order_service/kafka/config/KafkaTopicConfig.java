package com.suleyman6001.order_service.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderCreatedTopic( @Value("${app.kafka.topics.order-created}") String topicName) {
        return new NewTopic(topicName, 1, (short) 1);
    }

}
