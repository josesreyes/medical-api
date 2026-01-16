package com.jsrdev.medapi.usecase.patient.create;

import com.jsrdev.medapi.domain.exception.PatientAlreadyExistsException;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.repository.PatientRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePatientService implements CreatePatientUseCase {

    private final PatientRepositoryPort patientRepositoryPort;

    @Transactional
    @Override
    public Patient execute(Patient patient) {
        ensurePatientDoesNotExist(patient);

        return patientRepositoryPort.create(patient);
    }

    private void ensurePatientDoesNotExist(Patient patient) {
        if (patientRepositoryPort.existsByEmail(patient.getEmail())) {
            throw new PatientAlreadyExistsException("email", patient.getEmail().value());
        }
        if (patientRepositoryPort.existsByIdentityDocument(patient.getIdentityDocument())) {
            throw new PatientAlreadyExistsException("identityDocument", patient.getIdentityDocument());
        }
        if (patientRepositoryPort.existsByPhoneNumber(patient.getPhoneNumber())) {
            throw new PatientAlreadyExistsException("phoneNumber",  patient.getPhoneNumber().value());
        }
    }
}
