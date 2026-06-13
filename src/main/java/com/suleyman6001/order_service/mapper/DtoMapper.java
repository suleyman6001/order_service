package com.suleyman6001.order_service.mapper;

import com.suleyman6001.order_service.dto.request.OrderCreationRequestDto;
import com.suleyman6001.order_service.dto.response.OrderResponseDto;
import com.suleyman6001.order_service.entity.Order;
import com.suleyman6001.order_service.entity.Status;

import java.time.LocalDateTime;

public class DtoMapper {

    public static OrderResponseDto fromEntityToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();

        dto.setCustomerId(order.getCustomerId());
        dto.setProductCode(order.getProductCode());
        dto.setRequestedQuantity(order.getQuantity());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());

        return dto;
    }

    public static Order fromDtoToEntity(OrderCreationRequestDto creationRequestDto) {
        Order order = new Order();
        String normalizedProductCode = creationRequestDto.getProductCode().trim().toUpperCase();

        order.setProductCode(normalizedProductCode);
        order.setCustomerId(creationRequestDto.getCustomerId());
        order.setQuantity(creationRequestDto.getRequestedQuantity());
        order.setStatus(Status.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        return order;
    }

}
