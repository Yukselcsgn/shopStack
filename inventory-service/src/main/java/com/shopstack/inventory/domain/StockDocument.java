package com.shopstack.inventory.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stocks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDocument {
    @Id
    private String id;

    private String productId;
    private Integer quantity;
}
