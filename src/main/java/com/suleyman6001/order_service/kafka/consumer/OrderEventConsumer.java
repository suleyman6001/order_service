package com.suleyman6001.order_service.kafka.consumer;

import com.suleyman6001.order_service.kafka.event.InventoryItemReservationResultEvent;
import com.suleyman6001.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OrderEventConsumer.class);
    private final OrderRepository orderRepository;

    @Autowired
    public OrderEventConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "${app.kafka.topics.inventory-item-reservation-result}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeInventoryItemReservationResultEvent(InventoryItemReservationResultEvent resultEvent) {
        // TODO implement the logic
    }
}
