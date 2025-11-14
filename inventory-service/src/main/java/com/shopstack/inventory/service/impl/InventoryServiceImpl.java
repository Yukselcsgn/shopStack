package com.shopstack.inventory.service.impl;

import com.shopstack.inventory.dto.StockRequest;
import com.shopstack.inventory.dto.StockResponse;
import com.shopstack.inventory.domain.StockDocument;
import com.shopstack.inventory.entity.OutboxEventDocument;
import com.shopstack.inventory.exception.StockNotFoundException;
import com.shopstack.inventory.mapper.StockMapper;
import com.shopstack.inventory.repository.OutboxRepository;
import com.shopstack.inventory.repository.StockRepository;
import com.shopstack.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final StockRepository stockRepository;
    private final OutboxRepository outboxRepository;

    @Override
    public StockResponse getStock(String productId) {
        StockDocument stock = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new StockNotFoundException("Stock not found for productId: " + productId));
        return StockMapper.toResponse(stock);
    }

    @Override
    public void updateStock(StockRequest request) {
        StockDocument stock = stockRepository.findByProductId(request.getProductId())
                .orElse(StockDocument.builder()
                        .productId(request.getProductId())
                        .quantity(0)
                        .build());

        stock.setQuantity(request.getQuantity());
        stockRepository.save(stock);
        log.info("Updated stock for product {} -> {}", request.getProductId(), request.getQuantity());
    }

    /**
     * Attempts to reserve stock for an order.
     * Writes an outbox event (STOCK_RESERVED or STOCK_NOT_AVAILABLE).
     */
    @Override
    @Transactional
    public void reserveStock(String orderId, String productId, Integer quantity) {
        StockDocument stock = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new StockNotFoundException("Stock not found for productId: " + productId));

        if (stock.getQuantity() >= quantity) {
            stock.setQuantity(stock.getQuantity() - quantity);
            stockRepository.save(stock);
            OutboxEventDocument e = OutboxEventDocument.stockReserved(orderId, productId, quantity);
            outboxRepository.save(e);
            log.info("Reserved {} units for product {} (order {}). New qty: {}", quantity, productId, orderId, stock.getQuantity());
        } else {
            OutboxEventDocument e = OutboxEventDocument.stockNotAvailable(orderId, productId);
            outboxRepository.save(e);
            log.info("Not enough stock for product {} (order {}). Available: {}, Requested: {}", productId, orderId, stock.getQuantity(), quantity);
        }
    }
}
