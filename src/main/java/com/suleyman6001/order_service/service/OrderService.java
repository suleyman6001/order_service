package com.suleyman6001.order_service.service;

import com.suleyman6001.order_service.dto.request.OrderCreationRequestDto;
import com.suleyman6001.order_service.kafka.event.OrderCreatedEvent;
import com.suleyman6001.order_service.dto.response.OrderResponseDto;
import com.suleyman6001.order_service.entity.Order;
import com.suleyman6001.order_service.entity.Status;
import com.suleyman6001.order_service.kafka.producer.OrderEventProducer;
import com.suleyman6001.order_service.mapper.DtoMapper;
import com.suleyman6001.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderEventProducer orderEventProducer) {
        this.orderRepository = orderRepository;
        this.orderEventProducer = orderEventProducer;
    }

    public OrderResponseDto processIncomingOrder(OrderCreationRequestDto creationRequestDto) {
        Order order = persistOrder(creationRequestDto);
        logger.info("Order persisted: {}", order);

        OrderResponseDto responseDto = DtoMapper.fromEntityToDto(order);

        // Sending order-created event to kafka broker
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(order.getId(), order.getProductCode(), order.getQuantity(), order.getCustomerId());
        orderEventProducer.sendOrderCreatedEvent(orderCreatedEvent);
        logger.info("Producer produced the OrderCreatedEvent");

        return responseDto;
    }

    private Order persistOrder(OrderCreationRequestDto creationRequestDto) {
        Order order = DtoMapper.fromDtoToEntity(creationRequestDto);

        // persist the order
        orderRepository.save(order);

        return order;
    }

}
