package com.jsrdev.medapi.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jsrdev.medapi.domain.repository.JwtProviderPort;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class JwtTokenService implements JwtProviderPort {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private Long expiration;

    private String issuer;
    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC256(secret);
        this.issuer = "jsr_dev";
    }

    @Override
    public String generateToken(UUID userId, String login) {

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(login)
                .withClaim("userId", userId.toString())
                .withExpiresAt(Instant.now().plusSeconds(expiration))
                .sign(algorithm);
    }

    @Override
    public UUID validateToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT
                    .require(algorithm)
                    .build()
                    .verify(token);

            return UUID.fromString(decodedJWT.getClaim("userId").asString());
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    @Override
    public String getSubject(String token) {
        return decodeToken(token).getSubject();
    }

    private DecodedJWT decodeToken(String token) {
        try {
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .withIssuer(issuer)
                    .build();

            return verifier.verify(token);
        } catch (JWTVerificationException ex) {
            throw new IllegalArgumentException("Invalid or Expired Token");
        }
    }
}
