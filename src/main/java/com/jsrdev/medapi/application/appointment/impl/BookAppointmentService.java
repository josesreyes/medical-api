package com.jsrdev.medapi.application.appointment.impl;

import com.jsrdev.medapi.application.appointment.BookAppointmentUseCase;
import com.jsrdev.medapi.domain.exception.AppointmentAlreadyExistsException;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.exception.IntegrityValidationException;
import com.jsrdev.medapi.domain.model.appointment.Appointment;
import com.jsrdev.medapi.domain.port.out.AppointmentRepositoryPort;
import com.jsrdev.medapi.domain.port.out.PatientRepositoryPort;
import com.jsrdev.medapi.domain.port.out.PhysicianAvailabilityPort;
import com.jsrdev.medapi.domain.port.out.PhysicianRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BookAppointmentService implements BookAppointmentUseCase {

    private final AppointmentRepositoryPort appointmentRepository;
    private final PhysicianRepositoryPort physicianRepository;
    private final PatientRepositoryPort patientRepository;
    private final PhysicianAvailabilityPort physicianAvailability;

    @Override
    @Transactional
    public Appointment book(UUID physicianId, UUID patientId, LocalDateTime date) {

        var physician = physicianRepository.findById(physicianId)
                .orElseThrow(() -> new EntityNotFoundException("Physician not found: " + physicianId));

        if (!Boolean.TRUE.equals(physician.getIsActive()))
            throw new IntegrityValidationException("Physician is not active: " + physicianId);

        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + patientId));

        if (!Boolean.TRUE.equals(patient.getIsActive()))
            throw new IntegrityValidationException("Patient is not active: " + patientId);

        if (!physicianAvailability.isAvailable(physicianId, date))
            throw new AppointmentAlreadyExistsException("date", date.toString());

        // El constructor de Appointment valida que la fecha sea futura
        Appointment appointment = new Appointment(physicianId, patientId, date);
        return appointmentRepository.reserve(appointment);
    }
}
