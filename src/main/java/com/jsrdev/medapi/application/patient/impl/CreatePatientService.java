package com.jsrdev.medapi.application.patient.impl;

import com.jsrdev.medapi.domain.exception.PatientAlreadyExistsException;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.port.out.PatientRepositoryPort;
import com.jsrdev.medapi.application.patient.CreatePatientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePatientService implements CreatePatientUseCase {

    private final PatientRepositoryPort patientRepository;

    @Transactional
    @Override
    public Patient create(Patient patient) {
        ensurePatientDoesNotExist(patient);

        return patientRepository.create(patient);
    }

    private void ensurePatientDoesNotExist(Patient patient) {
        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new PatientAlreadyExistsException("email", patient.getEmail().value());
        }
        if (patientRepository.existsByIdentityDocument(patient.getIdentityDocument())) {
            throw new PatientAlreadyExistsException("identityDocument", patient.getIdentityDocument());
        }
        if (patientRepository.existsByPhoneNumber(patient.getPhoneNumber())) {
            throw new PatientAlreadyExistsException("phoneNumber",  patient.getPhoneNumber().value());
        }
    }
}
