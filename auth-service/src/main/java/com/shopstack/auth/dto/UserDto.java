package com.shopstack.auth.dto;

import java.util.Set;

public record UserDto(
        Long id,
        String email,
        Set<RoleDto> roles
) {}
