package com.jsrdev.medapi.usecase.patient.imp;

import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.repository.PatientRepositoryPort;
import com.jsrdev.medapi.usecase.patient.GetActivePatients;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetActivePatientsInteractor implements GetActivePatients {

    PatientRepositoryPort patientRepositoryPort;

    public GetActivePatientsInteractor(PatientRepositoryPort patientRepositoryPort) {
        this.patientRepositoryPort = patientRepositoryPort;
    }

    @Override
    public Page<Patient> getActivePatients(int page, int size) {
        validateRequest(page, size);

        Page<Patient> patients = loadActivePatients(page, size);

        validateResult(patients);

        return patients;
    }

    @Override
    public Patient getPatientById(UUID id) {
        return validateIfExists(id);
    }

    private Patient validateIfExists(UUID id) {
        if (patientRepositoryPort.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Patient with id " + id + " not found");
        }
        return patientRepositoryPort.findById(id).get();
    }

    /* ===================== INTENTIONS ===================== */

    private void validateRequest(int page, int size) {
        if (page < 0) {
            throw new ValidationException("Page index must be >= 0");
        }
        if (size <= 0) {
            throw new ValidationException("Page size must be greater than 0");
        }
    }

    private Page<Patient> loadActivePatients(int page, int size) {
        return patientRepositoryPort.findActivePatients(PageRequest.of(page, size));
    }

    private void validateResult(Page<Patient> patients) {
        if (patients.isEmpty()) {
            throw new EntityNotFoundException("No active patients found");
        }
    }
}
