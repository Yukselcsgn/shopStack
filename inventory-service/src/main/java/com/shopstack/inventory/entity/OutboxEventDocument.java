package com.shopstack.inventory.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "outbox_events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEventDocument {
    @Id
    private String id;

    private String type;
    private String payload; // JSON string
    private Instant createdAt;
    private boolean published;

    public static OutboxEventDocument stockReserved(String orderId, String productId, Integer quantity) {
        String payload = String.format("{\"orderId\":\"%s\",\"productId\":\"%s\",\"quantity\":%d}", orderId, productId, quantity);
        return OutboxEventDocument.builder()
                .type("STOCK_RESERVED")
                .payload(payload)
                .createdAt(Instant.now())
                .published(false)
                .build();
    }

    public static OutboxEventDocument stockNotAvailable(String orderId, String productId) {
        String payload = String.format("{\"orderId\":\"%s\",\"productId\":\"%s\"}", orderId, productId);
        return OutboxEventDocument.builder()
                .type("STOCK_NOT_AVAILABLE")
                .payload(payload)
                .createdAt(Instant.now())
                .published(false)
                .build();
    }
}
