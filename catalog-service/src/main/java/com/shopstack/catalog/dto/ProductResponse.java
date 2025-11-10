package com.shopstack.catalog.dto;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock,
        CategoryDto category,
        BrandDto brand
) {}