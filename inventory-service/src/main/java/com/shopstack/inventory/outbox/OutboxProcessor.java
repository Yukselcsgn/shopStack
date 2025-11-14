package com.shopstack.inventory.outbox;

import com.shopstack.inventory.entity.OutboxEventDocument;
import com.shopstack.inventory.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxProcessor {

    private final OutboxRepository outboxRepository;
    private final OutboxPublisher outboxPublisher;

    @Value("${inventory.outbox.polling-interval-ms:2000}")
    private long pollingIntervalMs;

    /**
     * Polls the outbox collection periodically and publishes unpublished events.
     * After successful publish, marks published = true.
     */
    @Scheduled(fixedDelayString = "${inventory.outbox.polling-interval-ms:2000}")
    public void processOutbox() {
        List<OutboxEventDocument> events = outboxRepository.findTop50ByPublishedFalseOrderByCreatedAtAsc();
        if (events.isEmpty()) return;

        log.info("Processing {} outbox events", events.size());
        for (OutboxEventDocument e : events) {
            try {
                outboxPublisher.publish(e);
                e.setPublished(true);
                outboxRepository.save(e);
            } catch (Exception ex) {
                log.error("Failed to publish outbox event id {}: {}", e.getId(), ex.getMessage());
                // publish başarısızsa bırakıyoruz, sonraki döngüde tekrar denenecek
            }
        }
    }
}
