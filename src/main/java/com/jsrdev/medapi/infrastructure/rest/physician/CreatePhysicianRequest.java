package com.jsrdev.medapi.infrastructure.rest.physician;

import com.jsrdev.medapi.domain.model.physician.Specialty;
import com.jsrdev.medapi.infrastructure.rest.address.AddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record CreatePhysicianRequest(
        @NotBlank(message = "Name required")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Invalid format")
        String name,

        @Size(max = 300)
        @Pattern(regexp = "^(https?:\\/\\/).+", message = "Avatar must be a valid URL")
        String avatar,

        @Email(message = "Email invalid")
        @NotBlank(message = "Email required")
        String email,

        @NotBlank(message = "Document required")
        @Pattern(regexp = "\\d{7,20}", message = "Invalid format")
        String document,

        @NotBlank(message = "PhoneNumber required")
        @Pattern(regexp = "\\d{10,15}", message = "Invalid format")
        String phoneNumber,

        @NotNull(message = "Specialty required")
        Specialty specialty,

        @Valid @NotNull(message = "Address required")
        AddressRequest address
) {
}
