package com.jsrdev.medapi.infrastructure.web.appointment.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookAppointmentRequest(
        @NotNull(message = "Physician ID is required")
        UUID physicianId,

        @NotNull(message = "Patient ID is required")
        UUID patientId,

        @NotNull(message = "Date is required")
        @Future(message = "Appointment date must be in the future")
        LocalDateTime date
) {
}
