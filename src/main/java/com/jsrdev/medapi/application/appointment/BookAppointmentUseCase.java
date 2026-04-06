package com.jsrdev.medapi.application.appointment;

import com.jsrdev.medapi.domain.model.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Reglas de negocio:
 *  1. Médico existe y está activo.
 *  2. Paciente existe y está activo.
 *  3. Médico libre en esa fecha/hora (sin doble booking).
 *  4. Fecha futura (invariante en Appointment).
 */
public interface BookAppointmentUseCase {
    Appointment book(UUID physicianId, UUID patientId, LocalDateTime date);
}