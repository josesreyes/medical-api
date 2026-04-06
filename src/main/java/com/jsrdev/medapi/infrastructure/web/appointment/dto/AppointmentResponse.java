package com.jsrdev.medapi.infrastructure.web.appointment.dto;

import com.jsrdev.medapi.domain.common.AppointmentStatus;
import com.jsrdev.medapi.domain.common.CancellationReason;
import com.jsrdev.medapi.domain.model.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponse(
        UUID id,
        UUID physicianId,
        UUID patientId,
        LocalDateTime date,
        AppointmentStatus status,
        CancellationReason cancellationReason,
        LocalDateTime cancellationDate
) {
    public static AppointmentResponse from(Appointment a) {
        return new AppointmentResponse(
                a.getId(), a.getPhysicianId(), a.getPatientId(),
                a.getDate(), a.getStatus(),
                a.getCancellationReason(), a.getCancellationDate()
        );
    }
}
