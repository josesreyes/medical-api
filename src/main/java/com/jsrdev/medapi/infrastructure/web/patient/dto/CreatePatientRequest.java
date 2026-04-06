package com.jsrdev.medapi.infrastructure.web.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePatientRequest(
        @NotBlank(message = "Name is required")
        String name,

        String avatar,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Identity document is required")
        String identityDocument,

        @NotBlank(message = "Phone number is required")
        String phoneNumber,

        @NotNull(message = "Address is required")
        @Valid
        PatientAddressRequest address
) {
}
