package com.shopstack.auth.service;

import com.shopstack.auth.dto.LoginRequest;
import com.shopstack.auth.dto.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(String refreshToken);

    void revokeRefreshToken(String refreshToken);
}
