package com.jsrdev.medapi.infrastructure.rest.dto;

import com.jsrdev.medapi.domain.model.physician.Specialty;

public record CreatePhysicianRequest(
        String name,
        String avatar,
        String email,
        String document,
        String phoneNumber,
        Specialty specialty,
        AddressRequest address
) {
}
