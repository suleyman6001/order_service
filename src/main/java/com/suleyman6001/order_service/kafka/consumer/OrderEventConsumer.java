package com.suleyman6001.order_service.kafka.consumer;

import com.suleyman6001.order_service.entity.Order;
import com.suleyman6001.order_service.entity.Status;
import com.suleyman6001.order_service.kafka.event.InventoryItemReservationResultEvent;
import com.suleyman6001.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Long orderId = resultEvent.getOrderId();
        String productCode = resultEvent.getProductCode();
        Integer requestedQuantity = resultEvent.getRequestedQuantity();
        String customerId = resultEvent.getCustomerId();
        Boolean reserved = resultEvent.getReserved();
        String message = resultEvent.getMessage();

        logger.info("Inside consumeInventoryItemReservationResultEvent() method");
        logger.info(resultEvent.toString());

        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            return;
        }

        if (!reserved) {
            order.setStatus(Status.REJECTED);
        }
        else {
            order.setStatus(Status.CONFIRMED);
        }

        orderRepository.save(order);
    }
}
