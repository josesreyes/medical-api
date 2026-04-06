package com.jsrdev.medapi.application.patient;

import java.util.UUID;

public interface DeactivatePatientUseCase {
    void deactivate(UUID id);
}
