package com.jsrdev.medapi.infrastructure.rest.physician;

import com.jsrdev.medapi.infrastructure.rest.address.AddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdatePhysicianRequest(
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Invalid format")
        String name,

        @Size(max = 300)
        @Pattern(regexp = "^(https?:\\/\\/).+", message = "Avatar must be a valid URL")
        String avatar,

        @Pattern(regexp = "\\d{10,15}", message = "Invalid format")
        String phoneNumber,

        @Valid
        AddressRequest address
) {
}
