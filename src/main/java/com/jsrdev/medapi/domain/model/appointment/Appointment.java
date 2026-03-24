package com.jsrdev.medapi.domain.model.appointment;

import com.jsrdev.medapi.domain.common.AppointmentStatus;
import com.jsrdev.medapi.domain.common.CancellationReason;
import jakarta.validation.ValidationException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Appointment {
    private UUID id;
    private UUID physicianId;
    private UUID patientId;
    private LocalDateTime date;
    private AppointmentStatus status;
    private CancellationReason cancellationReason;
    private LocalDateTime cancellationDate;

    public Appointment(
            UUID physicianId,
            UUID patientId,
            LocalDateTime date
    ) {
        if (date.isBefore(LocalDateTime.now())) {
            throw new ValidationException("Appointment date must be in the future");
        }

        this.id = UUID.randomUUID();
        this.physicianId = physicianId;
        this.patientId = patientId;
        this.date = date;
        this.status = AppointmentStatus.SCHEDULED;
    }

    public void cancel(CancellationReason reason) {
        if (this.status == AppointmentStatus.CANCELLED) {
            throw new ValidationException("Appointment already cancelled");
        }

        if (this.status == AppointmentStatus.COMPLETED) {
            throw new ValidationException("Completed appointment cannot be cancelled");
        }

        this.status = AppointmentStatus.CANCELLED;
        this.cancellationReason = reason;
        this.cancellationDate = LocalDateTime.now();
    }

    public void complete() {
        if (this.status != AppointmentStatus.SCHEDULED) {
            throw new ValidationException("Only scheduled appointments can be completed");
        }

        this.status = AppointmentStatus.COMPLETED;
    }


}
