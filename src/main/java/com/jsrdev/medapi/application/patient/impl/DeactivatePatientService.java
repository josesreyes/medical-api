package com.jsrdev.medapi.application.patient.impl;

import com.jsrdev.medapi.application.patient.DeactivatePatientUseCase;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.port.out.PatientRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeactivatePatientService implements DeactivatePatientUseCase {

    private final PatientRepositoryPort patientRepository;

    @Override
    @Transactional
    public void deactivate(UUID id) {
        patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + id));
        patientRepository.deactivate(id);
    }
}
