package com.jsrdev.medapi.application.patient;

import com.jsrdev.medapi.domain.model.patient.Patient;

import java.util.UUID;

public interface UpdatePatientUseCase {
    Patient update(UUID id, Patient incoming);
}
