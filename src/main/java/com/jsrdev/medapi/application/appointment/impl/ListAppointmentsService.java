package com.jsrdev.medapi.application.appointment.impl;

import com.jsrdev.medapi.application.appointment.ListAppointmentsUseCase;
import com.jsrdev.medapi.domain.model.appointment.Appointment;
import com.jsrdev.medapi.domain.port.out.AppointmentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListAppointmentsService implements ListAppointmentsUseCase {

    private final AppointmentRepositoryPort appointmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> listAll() {
        LocalDateTime start = LocalDateTime.now().minusMonths(1);
        LocalDateTime end   = LocalDateTime.now().plusMonths(6);
        return appointmentRepository.findAllByDateRange(start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> listByPhysician(UUID physicianId) {
        LocalDateTime start = LocalDateTime.now().minusMonths(1);
        LocalDateTime end   = LocalDateTime.now().plusMonths(6);
        return appointmentRepository.findByPhysicianAndDateRange(physicianId, start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> listByPatient(UUID patientId) {
        LocalDateTime start = LocalDateTime.now().minusMonths(1);
        LocalDateTime end   = LocalDateTime.now().plusMonths(6);
        return appointmentRepository.findByPatientAndDateRange(patientId, start, end);
    }
}
