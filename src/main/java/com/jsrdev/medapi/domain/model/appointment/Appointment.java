package com.jsrdev.medapi.domain.model.appointment;

import com.jsrdev.medapi.domain.common.AppointmentStatus;
import com.jsrdev.medapi.domain.common.CancellationReason;
import com.jsrdev.medapi.domain.exception.InvalidAppointmentDataException;
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

    // ── Constructor: nueva cita (valida invariantes de negocio) ─────────────
    public Appointment(UUID physicianId, UUID patientId, LocalDateTime date) {
        if (physicianId == null) throw new InvalidAppointmentDataException("Physician ID required");
        if (patientId == null) throw new InvalidAppointmentDataException("Patient ID required");
        if (date == null) throw new InvalidAppointmentDataException("Date required");
        if (date.isBefore(LocalDateTime.now()))
            throw new InvalidAppointmentDataException("Appointment date must be in the future");

        this.id = null;
        this.physicianId = physicianId;
        this.patientId = patientId;
        this.date = date;
        this.status = AppointmentStatus.SCHEDULED;
    }

    // ── Constructor privado solo para reconstitución desde persistencia ──────
    private Appointment() {
    }

    /**
     * Reconstruye una cita ya existente en DB sin pasar por validaciones del
     * constructor principal (la fecha puede ser pasada en historial).
     * Solo debe llamarse desde AppointmentPersistenceMapper.
     */
    public static Appointment reconstitute(
            UUID id,
            UUID physicianId,
            UUID patientId,
            LocalDateTime date,
            AppointmentStatus status,
            CancellationReason cancellationReason,
            LocalDateTime cancellationDate
    ) {
        Appointment a = new Appointment();
        a.id = id;
        a.physicianId = physicianId;
        a.patientId = patientId;
        a.date = date;
        a.status = status;
        a.cancellationReason = cancellationReason;
        a.cancellationDate = cancellationDate;
        return a;
    }

    // ── Comportamiento de dominio ────────────────────────────────────────────

    /**
     * Asigna el UUID antes de persistir por primera vez.
     */
    public void assignId(UUID id) {
        if (this.id != null) throw new InvalidAppointmentDataException("ID already assigned");
        this.id = id;
    }

    public void cancel(CancellationReason reason) {
        if (reason == null)
            throw new InvalidAppointmentDataException("Cancellation reason is required");
        if (this.status == AppointmentStatus.CANCELLED)
            throw new InvalidAppointmentDataException("Appointment is already cancelled");
        if (this.status == AppointmentStatus.COMPLETED)
            throw new InvalidAppointmentDataException("Completed appointment cannot be cancelled");

        this.status = AppointmentStatus.CANCELLED;
        this.cancellationReason = reason;
        this.cancellationDate = LocalDateTime.now();
    }

    public void complete() {
        if (this.status != AppointmentStatus.SCHEDULED)
            throw new InvalidAppointmentDataException("Only SCHEDULED appointments can be completed");
        this.status = AppointmentStatus.COMPLETED;
    }
}
