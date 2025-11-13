package com.shopstack.inventory.repository;

import com.shopstack.inventory.domain.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, UUID>{

    Optional<InventoryItem> findBySku(String sku);
    boolean existsBySku(String sku);
}
