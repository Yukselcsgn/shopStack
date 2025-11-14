package com.shopstack.inventory.repository;

import com.shopstack.inventory.domain.StockDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends MongoRepository<StockDocument, String> {
    Optional<StockDocument> findByProductId(String productId);
}

