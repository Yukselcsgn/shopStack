package com.shopstack.auth.repository;

import com.shopstack.auth.domain.RefreshToken;
import com.shopstack.auth.domain.TokenStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    long countByUserIdAndStatus(Long userId, TokenStatus status);
}

//findByToken → Token yenileme (refresh) işleminde kullanılır.

//countByUserIdAndStatus → Aynı kullanıcı için birden fazla aktif refresh token açılmasını engellemek amacıyla kullanılabilir