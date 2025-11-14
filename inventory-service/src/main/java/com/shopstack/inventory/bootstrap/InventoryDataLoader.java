package com.shopstack.inventory.bootstrap;

import com.shopstack.inventory.domain.StockDocument;
import com.shopstack.inventory.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryDataLoader implements CommandLineRunner {

    private final StockRepository stockRepository;

    @Override
    public void run(String... args) throws Exception {
        if (stockRepository.count() == 0) {
            stockRepository.save(StockDocument.builder().productId("p-100").quantity(10).build());
            stockRepository.save(StockDocument.builder().productId("p-200").quantity(5).build());
        }
    }
}
