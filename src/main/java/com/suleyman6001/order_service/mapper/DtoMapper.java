package com.suleyman6001.order_service.mapper;

import com.suleyman6001.order_service.dto.response.OrderResponseDto;
import com.suleyman6001.order_service.entity.Order;

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

}
