package com.suleyman6001.order_service.kafka.event;

public record InventoryItemReservationResultEvent(
        Long orderId,
        String productCode,
        Integer requestedQuantity,
        Boolean reserved,
        String reason
) {
}
