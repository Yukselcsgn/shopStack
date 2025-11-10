package com.shopstack.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotBlank
        String name,

        String description,

        @NotNull
        Double price,

        @NotNull
        Integer stock,

        @NotNull
        Long categoryId,

        @NotNull
        Long brandId
) { }