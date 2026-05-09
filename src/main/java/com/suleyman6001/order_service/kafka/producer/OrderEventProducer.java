package com.suleyman6001.order_service.kafka.producer;

import com.suleyman6001.order_service.kafka.event.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String orderCreatedTopic;

    @Autowired
    public OrderEventProducer(KafkaTemplate<String, Object> kafkaTemplate,
                              @Value("${app.kafka.topics.order-created}") String orderCreatedTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderCreatedTopic = orderCreatedTopic;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) {
        kafkaTemplate.send(orderCreatedTopic, String.valueOf(orderCreatedEvent.getOrderId()), orderCreatedEvent);
    }
}
