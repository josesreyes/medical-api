package com.jsrdev.medapi.infrastructure.rest.dto;

import com.jsrdev.medapi.domain.model.physician.Specialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePhysicianRequest(
        @NotBlank(message = "Name required")
        String name,
        String avatar,
        @Email(message = "Email invalid")
        @NotBlank(message = "Email required")
        String email,
        @NotBlank(message = "Document required")
        String document,
        @NotBlank(message = "PhoneNumber required")
        String phoneNumber,
        @NotNull(message = "Specialty required")
        Specialty specialty,
        @Valid
        AddressRequest address
) {
}
