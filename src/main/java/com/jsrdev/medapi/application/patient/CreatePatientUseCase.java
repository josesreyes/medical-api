package com.jsrdev.medapi.application.patient;

import com.jsrdev.medapi.domain.model.patient.Patient;

public interface CreatePatientUseCase {
    Patient create(Patient patient);
}
