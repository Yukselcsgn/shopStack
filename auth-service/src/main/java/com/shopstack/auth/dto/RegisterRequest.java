package com.shopstack.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.shopstack.auth.repository.UserRepository;

public record RegisterRequest(
        @Email
        String email,

        @NotBlank
        @Size(min = 6, max = 100)
        String password
){}