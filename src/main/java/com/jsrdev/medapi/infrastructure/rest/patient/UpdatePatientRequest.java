package com.jsrdev.medapi.infrastructure.rest.patient;

import com.jsrdev.medapi.infrastructure.rest.address.UpdateAddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdatePatientRequest(

        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Invalid format")
        String name,

        @Size(max = 300)
        @Pattern(regexp = "^(https?:\\/\\/).+", message = "Avatar must be a valid URL")
        String avatar,

        @Pattern(regexp = "\\d{10,15}", message = "Invalid format")
        String phoneNumber,

        @Valid
        UpdateAddressRequest address
) {
}
