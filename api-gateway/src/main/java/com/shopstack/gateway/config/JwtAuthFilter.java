package com.example.apigateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public GlobalFilter jwtFilter() {
        return (exchange, chain) -> {

            String path = exchange.getRequest().getURI().getPath();

            // Auth service public
            if (path.startsWith("/auth")) {
                return chain.filter(exchange);
            }

            // JWT kontrol
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            token = token.substring(7);

            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody();

                exchange.getRequest().mutate()
                        .header("X-User-Id", claims.getSubject())
                        .build();

            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        };
    }
}
