package com.shopstack.common.security;

import io.jsonwebtoken.*;
import java.util.Date;

public class JwtUtils {

    public static String generateToken(JwtUser user, String secret, long expirationMs) {
        return Jwts.builder()
                .setSubject(user.email())
                .claim("id", user.id())
                .claim("role", user.role())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static JwtUser parse(String token, String secret) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return new JwtUser(
                claims.get("id", Long.class),
                claims.getSubject(),
                claims.get("role", String.class)
        );
    }
}
