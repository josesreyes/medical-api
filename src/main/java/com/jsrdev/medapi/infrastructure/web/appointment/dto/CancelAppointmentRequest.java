package com.jsrdev.medapi.infrastructure.web.appointment.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Valores aceptados (ES o EN):
 *   "Paciente desistió" / "Patient desisted"
 *   "Médico canceló"   / "Physician desisted"
 *   "Otro"             / "Other"
 */
public record CancelAppointmentRequest(
        @NotBlank(message = "Cancellation reason is required")
        String cancellationReason
) {
}
