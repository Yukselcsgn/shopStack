package com.shopstack.inventory.exception;

public class StockNotFoundException extends InventoryException {
    public StockNotFoundException(String message) {
        super(message);
    }
}
