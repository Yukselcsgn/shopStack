package com.shopstack.inventory.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopstack.inventory.entity.OutboxEventDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Publishes raw payload to a topic derived from event type.
     */
    public void publish(OutboxEventDocument event) {
        try {
            String topic = topicForType(event.getType());
            String key = objectMapper.writeValueAsString(
                    objectMapper.readTree(event.getPayload()).path("orderId")
            ); // key can be orderId for partitioning; safe fallback if missing

            kafkaTemplate.send(topic, key, event.getPayload())
                    .addCallback(result -> log.info("Published event {} to topic {}", event.getType(), topic),
                            ex -> log.error("Failed to publish event {}: {}", event.getType(), ex.getMessage()));
        } catch (Exception ex) {
            log.error("Error while publishing outbox event: {}", ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    private String topicForType(String type) {
        // Basit eşleme; istersen application.yml'den çek
        switch (type) {
            case "STOCK_RESERVED":
                return "stock-reserved";
            case "STOCK_NOT_AVAILABLE":
                return "stock-not-available";
            default:
                return "inventory-events";
        }
    }
}
