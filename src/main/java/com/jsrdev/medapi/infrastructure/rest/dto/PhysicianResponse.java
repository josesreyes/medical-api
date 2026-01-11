package com.jsrdev.medapi.infrastructure.rest.dto;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.physician.Specialty;

import java.util.UUID;

public record PhysicianResponse(
        UUID id,
        String name,
        String avatar,
        Email email,
        String document,
        PhoneNumber phoneNumber,
        Specialty specialty,
        AddressResponse address
) {
}
