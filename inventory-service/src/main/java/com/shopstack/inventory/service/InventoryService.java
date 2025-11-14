package com.shopstack.inventory.service;

import com.shopstack.inventory.dto.StockRequest;
import com.shopstack.inventory.dto.StockResponse;

public interface InventoryService {
    StockResponse getStock(String productId);
    void updateStock(StockRequest request);
    void reserveStock(String orderId, String productId, Integer quantity);
}
