package com.jsrdev.medapi.infrastructure.rest.physician;

import com.jsrdev.medapi.domain.model.physician.Specialty;
import com.jsrdev.medapi.infrastructure.rest.address.AddressResponse;

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
