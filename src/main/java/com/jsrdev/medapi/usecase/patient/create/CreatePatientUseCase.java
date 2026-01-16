package com.jsrdev.medapi.usecase.patient.create;

import com.jsrdev.medapi.domain.model.patient.Patient;

public interface CreatePatientUseCase {
    Patient execute(Patient patient);
}
