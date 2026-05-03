package com.suleyman6001.order_service.kafka.producer;

import com.suleyman6001.order_service.kafka.event.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    @Value("${app.kafka.topics.order-created}")
    private String ORDER_CREATED_TOPIC;
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Autowired
    public OrderEventProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) {
        kafkaTemplate.send(ORDER_CREATED_TOPIC, String.valueOf(orderCreatedEvent.orderId()), orderCreatedEvent);
    }
}
