package com.jsrdev.medapi.usecase.patient;

import com.jsrdev.medapi.domain.model.patient.Patient;

public interface CreatePatient {
    Patient execute(Patient patient);
}
