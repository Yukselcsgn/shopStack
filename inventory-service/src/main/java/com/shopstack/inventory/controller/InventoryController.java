package com.shopstack.inventory.controller;

import com.shopstack.inventory.dto.StockRequest;
import com.shopstack.inventory.dto.StockResponse;
import com.shopstack.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{productId}")
    public ResponseEntity<StockResponse> getStock(@PathVariable String productId) {
        return ResponseEntity.ok(inventoryService.getStock(productId));
    }

    @PostMapping
    public ResponseEntity<Void> updateStock(@RequestBody StockRequest request) {
        inventoryService.updateStock(request);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint used by Order service (or tests) to reserve stock synchronously.
     * In production orders should call Order service and let it emit events consumed by inventory,
     * or call inventory via service-to-service with proper auth.
     */
    @PostMapping("/reserve")
    public ResponseEntity<Void> reserveStock(@RequestParam String orderId,
                                             @RequestParam String productId,
                                             @RequestParam Integer quantity) {
        inventoryService.reserveStock(orderId, productId, quantity);
        return ResponseEntity.ok().build();
    }
}
