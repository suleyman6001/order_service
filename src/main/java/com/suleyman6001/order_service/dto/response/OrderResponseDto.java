package com.suleyman6001.order_service.dto.response;

import com.suleyman6001.order_service.entity.Status;
import java.time.LocalDateTime;

public class OrderResponseDto {

    private Long orderId;
    private String productCode;
    private Integer requestedQuantity;
    private String customerId;
    private Status status;
    private LocalDateTime createdAt;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Long orderId, String productCode, Integer requestedQuantity, String customerId, Status status, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.productCode = productCode;
        this.requestedQuantity = requestedQuantity;
        this.customerId = customerId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(Integer requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OrderCreationResponseDto{" +
                "orderId='" + orderId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", requestedQuantity=" + requestedQuantity +
                ", customerId='" + customerId + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
