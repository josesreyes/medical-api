package com.jsrdev.medapi.usecase.patient;

import java.util.UUID;

public interface DeactivatePatient {
    void execute(UUID id);
}
