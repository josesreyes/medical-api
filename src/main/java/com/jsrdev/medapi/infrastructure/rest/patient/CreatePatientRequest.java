package com.jsrdev.medapi.infrastructure.rest.patient;

import com.jsrdev.medapi.infrastructure.rest.address.AddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record CreatePatientRequest(
        @NotBlank(message = "Name required")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Invalid format")
        String name,

        @Size(max = 300)
        @Pattern(regexp = "^(https?:\\/\\/).+", message = "Avatar must be a valid URL")
        String avatar,

        @Email(message = "Email invalid")
        @NotBlank(message = "Email required")
        String email,

        @NotBlank(message = "IdentityDocument required")
        @Pattern(regexp = "\\d{7,20}", message = "Invalid format")
        String identityDocument,

        @NotBlank(message = "PhoneNumber required")
        @Pattern(regexp = "\\d{10,15}", message = "Invalid format")
        String phoneNumber,

        @Valid @NotNull(message = "Address required")
        AddressRequest address
) {
}
