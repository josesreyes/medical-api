package com.jsrdev.medapi.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jsrdev.medapi.domain.exception.InvalidCredentialsException;
import com.jsrdev.medapi.domain.port.out.JwtProviderPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Component
public class JwtProviderAdapter implements JwtProviderPort {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private long expirationMs;

    @Override
    public String generateToken(UUID userId, String login) {
        return JWT.create()
                .withSubject(login)
                .withClaim("userId", userId.toString())
                .withExpiresAt(expirationInstant())
                .sign(algorithm());
    }

    @Override
    public UUID validateToken(String token) {
        try {
            String userId = JWT.require(algorithm())
                    .build()
                    .verify(token)
                    .getClaim("userId")
                    .asString();
            return UUID.fromString(userId);
        } catch (JWTVerificationException e) {
            throw new InvalidCredentialsException("Invalid or expired token");
        }
    }

    @Override
    public String getSubject(String token) {
        try {
            return JWT.require(algorithm()).build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            throw new InvalidCredentialsException("Invalid or expired token");
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }

    private Instant expirationInstant() {
        return LocalDateTime.now()
                .plusSeconds(expirationMs / 1000)
                .toInstant(ZoneOffset.UTC);
    }
}
