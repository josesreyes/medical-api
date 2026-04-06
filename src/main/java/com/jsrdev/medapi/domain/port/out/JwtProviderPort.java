package com.jsrdev.medapi.domain.port.out;

import java.util.UUID;

public interface JwtProviderPort {
    String generateToken(UUID userId, String login);
    UUID validateToken(String token);
    String getSubject(String token);
}
