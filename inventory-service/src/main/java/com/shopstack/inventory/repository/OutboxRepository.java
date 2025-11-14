package com.shopstack.inventory.repository;

import com.shopstack.inventory.entity.OutboxEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboxRepository extends MongoRepository<OutboxEventDocument, String> {
    List<OutboxEventDocument> findTop50ByPublishedFalseOrderByCreatedAtAsc();
}
