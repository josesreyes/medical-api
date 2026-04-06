package com.jsrdev.medapi.infrastructure.web.physician.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePhysicianRequest(
        @NotBlank(message = "Name is required")
        String name,

        String avatar,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Document is required")
        String document,

        @NotBlank(message = "Phone number is required")
        String phoneNumber,

        @NotBlank(message = "Specialty is required")
        String specialty,

        @NotNull(message = "Address is required")
        @Valid
        AddressRequest address
) {
}
