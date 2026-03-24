package com.jsrdev.medapi.infrastructure.rest.appointment;

import com.jsrdev.medapi.domain.model.appointment.Appointment;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.model.physician.Physician;

import java.time.LocalDateTime;

public class AppointmentDtoMapper {
    public static Appointment toDomain(Physician physician, Patient patient, LocalDateTime date) {
        return new Appointment(
                physician.getUuid(),
                patient.getUuid(),
                date
        );
    }
}
