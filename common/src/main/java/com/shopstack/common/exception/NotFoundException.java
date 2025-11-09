package com.shopstack.common.exception;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message, "NOT_FOUND");
    }
}
