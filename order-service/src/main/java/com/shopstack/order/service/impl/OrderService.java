package com.shopstack.order.service;

import com.shopstack.order.dto.OrderRequest;
import com.shopstack.order.dto.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    OrderResponse getOrderById(UUID id);
    List<OrderResponse> getOrdersByCustomer(UUID customerId);
    void cancelOrder(UUID id);
}
