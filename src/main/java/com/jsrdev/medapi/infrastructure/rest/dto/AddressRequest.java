package com.jsrdev.medapi.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddressRequest(
        @NotBlank(message = "Street required")
        @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,#\\-\\s]+$", message = "Invalid characters")
        String street,

        @NotBlank(message = "StateOrProvince required")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Invalid characters")
        String stateOrProvince,

        @NotBlank(message = "MunicipalityOrDelegation required")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Invalid characters")
        String municipalityOrDelegation,

        @NotBlank(message = "Country required")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Invalid characters")
        String country,

        @NotBlank(message = "City required")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Invalid characters")
        String city,

        @NotNull(message = "ZipCode required")
        Integer zipCode,

        @NotBlank(message = "ExternalNumber required")
        @Pattern(regexp = "^[a-zA-Z0-9\\-]+$", message = "Invalid characters")
        String externalNumber,

        @Pattern(regexp = "^[a-zA-Z0-9\\-]*$")
        String internalNumber,

        @NotBlank(message = "Complement required")
        @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,#\\-\\s]+$")
        String complement
) {
}
