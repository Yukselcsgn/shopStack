package com.shopstack.inventory.dto;

public class StockAdjustmentRequest {
    private String sku;
    private Long delta; // pozitif: ekleme, negatif: çıkarma
    private String reason;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getDelta() {
        return delta;
    }

    public void setDelta(Long delta) {
        this.delta = delta;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}