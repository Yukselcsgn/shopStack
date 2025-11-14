package com.shopstack.inventory.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockResponse {
    private String productId;
    private Integer quantity;
}
