package com.shopstack.auth.service.impl;

import com.shopstack.auth.domain.RefreshToken;
import com.shopstack.auth.domain.TokenStatus;
import com.shopstack.auth.domain.User;
import com.shopstack.auth.dto.AuthResponse;
import com.shopstack.auth.dto.LoginRequest;
import com.shopstack.auth.repository.RefreshTokenRepository;
import com.shopstack.auth.repository.UserRepository;
import com.shopstack.auth.security.JwtTokenProvider;
import com.shopstack.auth.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository,
                           RefreshTokenRepository refreshTokenRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshTokenStr = jwtTokenProvider.generateRefreshToken();

        RefreshToken refreshToken = new RefreshToken(
                refreshTokenStr,
                LocalDateTime.now().plusDays(7),
                user
        );

        refreshTokenRepository.save(refreshToken);

        return AuthResponse.of(accessToken, refreshTokenStr);
    }

    @Override
    public AuthResponse refreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));

        if (refreshToken.getStatus() != TokenStatus.ACTIVE) {
            throw new IllegalStateException("Token is not active");
        }

        if (refreshToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            refreshToken.setStatus(TokenStatus.EXPIRED);
            refreshTokenRepository.save(refreshToken);
            throw new IllegalStateException("Refresh token expired");
        }

        User user = refreshToken.getUser();

        String newAccessToken = jwtTokenProvider.generateAccessToken(user);
        String newRefresh = jwtTokenProvider.generateRefreshToken();

        // eski refresh token revoke edilir
        refreshToken.setStatus(TokenStatus.REVOKED);
        refreshTokenRepository.save(refreshToken);

        RefreshToken newToken = new RefreshToken(
                newRefresh,
                LocalDateTime.now().plusDays(7),
                user
        );

        refreshTokenRepository.save(newToken);

        return AuthResponse.of(newAccessToken, newRefresh);
    }

    @Override
    public void revokeRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .ifPresent(t -> {
                    t.setStatus(TokenStatus.REVOKED);
                    refreshTokenRepository.save(t);
                });
    }
}
