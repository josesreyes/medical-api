package com.jsrdev.medapi.usecase.patient;

import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.infrastructure.rest.patient.UpdatePatientRequest;

import java.util.UUID;

public interface UpdatePatient {
    Patient execute(UUID id, UpdatePatientRequest updateRequest);
}
