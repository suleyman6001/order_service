package com.suleyman6001.order_service.kafka.event;

public record OrderCreatedEvent(
    Long orderId,
    String productCode,
    Integer quantity,
    String customerId) { }
