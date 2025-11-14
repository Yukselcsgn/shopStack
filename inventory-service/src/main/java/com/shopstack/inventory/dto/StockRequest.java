package com.shopstack.inventory.dto;

import lombok.Data;

@Data
public class StockRequest {
    private String productId;
    private Integer quantity;
}
