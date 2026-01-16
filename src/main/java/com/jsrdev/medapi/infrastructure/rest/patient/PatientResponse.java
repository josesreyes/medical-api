package com.jsrdev.medapi.infrastructure.rest.patient;

import com.jsrdev.medapi.infrastructure.rest.address.AddressResponse;

import java.util.UUID;

public record PatientResponse(
        UUID uuid,
        String name,
        String avatar,
        String email,
        String document,
        String phoneNumber,
        AddressResponse address
) {
}
