package com.jsrdev.medapi.application.appointment;

import com.jsrdev.medapi.domain.common.CancellationReason;
import com.jsrdev.medapi.domain.model.appointment.Appointment;

import java.util.UUID;

public interface CancelAppointmentUseCase {
    Appointment cancel(UUID appointmentId, CancellationReason reason);
}