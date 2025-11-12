package com.shopstack.order.dto;

import com.shopstack.order.domain.OrderStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderResponse {
    private UUID id;
    private UUID customerId;
    private Double totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    public UUID getCustomerId() {return customerId;}
    public void setCustomerId(UUID customerId) {this.customerId = customerId;}

    public Double getTotalAmount() {return totalAmount;}
    public void setTotalAmount(Double totalAmount) {this.totalAmount = totalAmount;}

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}