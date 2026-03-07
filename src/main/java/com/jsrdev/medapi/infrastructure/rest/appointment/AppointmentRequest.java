package com.jsrdev.medapi.infrastructure.rest.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.jsrdev.medapi.domain.model.physician.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentRequest(
        @JsonAlias({"patientId", "patient_id"})
        @NotNull(message = "Is required")
        UUID patientId,

        @JsonAlias({"physicianId", "physician_id"})
        UUID physicianId,

        @NotNull(message = "Is required")
        @Future(message = "Must be in the future")
        // @JsonFormat(pattern = "dd/MM/yyyy HH:mm") // custom date time format
        LocalDateTime date, // 2025-07-21T02:00

        Specialty specialty
) {
}
