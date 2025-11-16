package com.shopstack.inventory.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopstack.inventory.entity.OutboxEventDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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
            );

            ListenableFuture<SendResult<String, String>> future = (ListenableFuture<SendResult<String, String>>) kafkaTemplate.send(topic, key, event.getPayload());
            future.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("Published event {} to topic {}", event.getType(), topic);
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.error("Failed to publish event {}: {}", event.getType(), ex.getMessage(), ex);
                }
            });

        } catch (Exception ex) {
            log.error("Error while publishing outbox event: {}", ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    private String topicForType(String type) {
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
