package com.jsrdev.medapi.infrastructure.rest.dto;

import com.jsrdev.medapi.domain.model.physician.Specialty;

import java.util.UUID;

public record PhysicianResponse(
        UUID id,
        String name,
        String avatar,
        String email,
        String document,
        String phoneNumber,
        Specialty specialty,
        AddressResponse address
) {
}
