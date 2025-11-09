package com.shopstack.common.exception;

public record ErrorResponse(
        String message,
        String code
) {}
