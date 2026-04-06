package com.jsrdev.medapi.application.patient.impl;

import com.jsrdev.medapi.application.patient.UpdatePatientUseCase;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.port.out.PatientRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdatePatientService implements UpdatePatientUseCase {

    private final PatientRepositoryPort patientRepository;

    @Override
    @Transactional
    public Patient update(UUID id, Patient incoming) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + id));

        if (incoming.getName() != null) existing.setName(incoming.getName());
        if (incoming.getAvatar() != null) existing.setAvatar(incoming.getAvatar());
        if (incoming.getPhoneNumber() != null) existing.setPhoneNumber(incoming.getPhoneNumber());
        if (incoming.getAddress() != null) existing.setAddress(incoming.getAddress());

        return patientRepository.update(existing);
    }
}
