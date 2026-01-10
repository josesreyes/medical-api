package com.jsrdev.medapi.infrastructure.rest.dto;

import com.jsrdev.medapi.common.Specialty;

public record CreatePhysicianRequest(
        String name,
        String avatar,
        String email,
        String document,
        String phoneNumber,
        Specialty specialty,
        CreateAddressRequest address
) {
}
