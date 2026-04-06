package com.jsrdev.medapi.domain.port.out;

import com.jsrdev.medapi.domain.model.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepositoryPort {

    Appointment reserve(Appointment appointment);

    boolean existByPhysicianAndDate(UUID physicianId, LocalDateTime date);

    Optional<Appointment> findById(UUID id);

    List<Appointment> findByPhysicianAndDateRange(UUID physicianId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByPatientAndDateRange(UUID patientId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findAllByDateRange(LocalDateTime start, LocalDateTime end);
}
