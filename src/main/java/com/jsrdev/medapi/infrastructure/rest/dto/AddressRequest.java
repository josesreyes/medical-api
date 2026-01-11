package com.jsrdev.medapi.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotBlank(message = "Street required")
        String street,
        @NotBlank(message = "StateOrProvince required")
        String stateOrProvince,
        @NotBlank(message = "MunicipalityOrDelegation required")
        String municipalityOrDelegation,
        @NotBlank(message = "Country required")
        String country,
        @NotBlank(message = "City required")
        String city,
        @NotNull(message = "ZipCode required")
        Integer zipCode,
        @NotBlank(message = "ExternalNumber required")
        String externalNumber,
        String internalNumber,
        @NotBlank(message = "Complement required")
        String complement
) {
}
