package com.shopstack.inventory.mapper;

import com.shopstack.inventory.domain.StockDocument;
import com.shopstack.inventory.dto.StockResponse;

public class StockMapper {
    public static StockResponse toResponse(StockDocument d) {
        return StockResponse.builder()
                .productId(d.getProductId())
                .quantity(d.getQuantity())
                .build();
    }
}
