package com.jsrdev.medapi.infrastructure.web.physician.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
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
