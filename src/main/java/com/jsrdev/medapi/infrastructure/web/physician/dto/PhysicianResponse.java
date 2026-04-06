package com.jsrdev.medapi.infrastructure.web.physician.dto;

import com.jsrdev.medapi.domain.model.physician.Physician;
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
        Boolean isActive,
        AddressResponse address
) {
    public static PhysicianResponse from(Physician p) {
        return new PhysicianResponse(
                p.getUuid(),
                p.getName(),
                p.getAvatar(),
                p.getEmail().value(),
                p.getDocument(),
                p.getPhoneNumber().value(),
                p.getSpecialty(),
                p.getIsActive(),
                AddressResponse.from(p.getAddress())
        );
    }
}
