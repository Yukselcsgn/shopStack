package com.shopstack.common.security;

public record JwtUser(
        Long id,
        String email,
        String role
) {}
