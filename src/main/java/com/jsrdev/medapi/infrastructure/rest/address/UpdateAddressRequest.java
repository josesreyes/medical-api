package com.jsrdev.medapi.infrastructure.rest.address;

import jakarta.validation.constraints.Pattern;

public record UpdateAddressRequest(
        @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,#\\-\\s]+$", message = "Invalid characters")
        String street,

        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Invalid characters")
        String stateOrProvince,

        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ.\\s]+$", message = "Invalid characters")
        String municipalityOrDelegation,

        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Invalid characters")
        String country,

        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Invalid characters")
        String city,

        @Pattern(regexp = "\\d{4,8}", message = "Invalid format")
        String zipCode,

        @Pattern(regexp = "^[a-zA-Z0-9\\-]+$", message = "Invalid characters")
        String externalNumber,

        @Pattern(regexp = "^[a-zA-Z0-9\\-]+$", message = "Invalid characters")
        String internalNumber,

        @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,#\\-\\s]+$", message = "Invalid characters")
        String complement
) {
}
