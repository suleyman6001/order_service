package com.suleyman6001.order_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class OrderCreationRequestDto {

    @NotBlank(message = "Product code is required")
    @Size(max = 100, message = "Product code must not exceed 100 characters")
    @Pattern(
            regexp = "^[A-Za-z0-9]+([_-][A-Za-z0-9]+)*$",
            message = "Product code must contain only letters, numbers, hyphens, and underscores"
    )
    private String productCode;
    private Integer requestedQuantity;
    private String customerId;

    public OrderCreationRequestDto() {
    }

    public OrderCreationRequestDto(String productCode, Integer requestedQuantity, String customerId) {
        this.productCode = productCode;
        this.requestedQuantity = requestedQuantity;
        this.customerId = customerId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(Integer requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    @Override
    public String toString() {
        return "OrderCreationRequestDto{" +
                "productCode='" + productCode + '\'' +
                ", requestedQuantity=" + requestedQuantity +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
