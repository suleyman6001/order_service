package com.suleyman6001.order_service.controller;

import com.suleyman6001.order_service.dto.request.OrderCreationRequestDto;
import com.suleyman6001.order_service.dto.response.OrderResponseDto;
import com.suleyman6001.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping_platform/order_service")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<OrderResponseDto> processOrder(@Valid @RequestBody OrderCreationRequestDto orderCreationRequestDto) {
        logger.info("Received the order from client");
        OrderResponseDto responseDto = orderService.processIncomingOrder(orderCreationRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}
