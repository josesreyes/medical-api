package com.jsrdev.medapi.infrastructure.web.patient.dto;

import jakarta.validation.Valid;

public record UpdatePatientRequest(
        String name,
        String avatar,
        String phoneNumber,
        @Valid PatientAddressRequest address
) {
}
