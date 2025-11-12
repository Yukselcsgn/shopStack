package com.shopstack.order.dto;

import java.util.UUID;

public class OrderRequest {
    private UUID customerId;
    private Double totalAmount;

    public UUID getCustomerId() { return customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

}