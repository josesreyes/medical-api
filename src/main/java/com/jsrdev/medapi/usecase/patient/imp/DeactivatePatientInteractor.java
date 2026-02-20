package com.jsrdev.medapi.usecase.patient.imp;

import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.repository.PatientRepositoryPort;
import com.jsrdev.medapi.usecase.patient.DeactivatePatient;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeactivatePatientInteractor implements DeactivatePatient {

    private final PatientRepositoryPort patientRepositoryPort;

    public DeactivatePatientInteractor(PatientRepositoryPort patientRepositoryPorxt) {
        this.patientRepositoryPort = patientRepositoryPorxt;
    }

    @Transactional
    @Override
    public void execute(UUID id) {
        Patient patient = loadPatient(id);

        ensureCanBeDeactivated(patient);

        patient.deactivate();

        patientRepositoryPort.update(patient);
    }

    private void ensureCanBeDeactivated(Patient patient) {
        if (!patient.getIsActive()) {
            throw new ValidationException("Patient is already inactive");
        }

        /*
         * Despues validar esto:
         * ensureNoActiveAppointments(patient);
         * */
    }

    private Patient loadPatient(UUID id) {
        return patientRepositoryPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
    }
}
