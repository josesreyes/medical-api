package com.jsrdev.medapi.application.appointment;

import com.jsrdev.medapi.domain.model.appointment.Appointment;

import java.util.List;
import java.util.UUID;

public interface ListAppointmentsUseCase {
    List<Appointment> listAll();
    List<Appointment> listByPhysician(UUID physicianId);
    List<Appointment> listByPatient(UUID patientId);
}
