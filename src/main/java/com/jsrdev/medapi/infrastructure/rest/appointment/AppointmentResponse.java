package com.jsrdev.medapi.infrastructure.rest.appointment;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponse(
        UUID id,
        UUID patientId,
        UUID physicianId,
        LocalDateTime date
) {
}
