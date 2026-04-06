package com.jsrdev.medapi.infrastructure.web.patient.dto;

import jakarta.validation.constraints.NotBlank;

public record PatientAddressRequest(
        @NotBlank String street,
        @NotBlank String stateOrProvince,
        @NotBlank String municipalityOrDelegation,
        @NotBlank String country,
        @NotBlank String city,
        @NotBlank String zipCode,
        @NotBlank String externalNumber,
        String internalNumber,
        String complement
) {
}
