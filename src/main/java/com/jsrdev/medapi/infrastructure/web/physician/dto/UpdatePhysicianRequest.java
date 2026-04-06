package com.jsrdev.medapi.infrastructure.web.physician.dto;

import jakarta.validation.Valid;

/**
 * Todos los campos son opcionales — solo se actualizan los que vienen non-null.
 */
public record UpdatePhysicianRequest(
        String name,
        String avatar,
        String phoneNumber,
        @Valid AddressRequest address
) {
}
