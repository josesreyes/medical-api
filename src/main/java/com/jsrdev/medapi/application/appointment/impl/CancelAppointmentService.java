package com.jsrdev.medapi.application.appointment.impl;

import com.jsrdev.medapi.application.appointment.CancelAppointmentUseCase;
import com.jsrdev.medapi.domain.common.CancellationReason;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.appointment.Appointment;
import com.jsrdev.medapi.domain.port.out.AppointmentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CancelAppointmentService implements CancelAppointmentUseCase {

    private final AppointmentRepositoryPort appointmentRepository;
    @Override
    @Transactional
    public Appointment cancel(UUID appointmentId, CancellationReason reason) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found: " + appointmentId));

        // Invariantes (ya cancelada / completada) las aplica el propio dominio
        appointment.cancel(reason);

        return appointmentRepository.reserve(appointment);
    }
}
