package com.shopstack.inventory.service;

import com.shopstack.inventory.dto.*;

import java.util.List;
import java.util.UUID;

public interface InventoryService{
    InventoryItemResponse createItem(InventoryItemRequest request);
    InventoryItemResponse getBySku(String sku);
    InventoryItemResponse getById(UUID id);
    ReserveStockResponse reserveStock(ReserveStockRequest request);
    void confirmReservation(UUID reservationId);
    void releaseReservation(UUID reservationId);
    void adjustStock(StockAdjustmentRequest request);
    List<InventoryItemResponse> listLowStock(Long threshold);
}