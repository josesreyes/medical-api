package com.jsrdev.medapi.usecase.patient;

import com.jsrdev.medapi.domain.model.patient.Patient;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface GetActivePatients {
    Page<Patient> getActivePatients(int page, int size);

    Patient getPatientById(UUID id);
}
