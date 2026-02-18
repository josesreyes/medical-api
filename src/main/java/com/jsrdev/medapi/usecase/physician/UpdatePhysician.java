package com.jsrdev.medapi.usecase.physician;

import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.infrastructure.rest.physician.UpdatePhysicianRequest;

import java.util.UUID;

public interface UpdatePhysician {
    Physician execute(UUID id, UpdatePhysicianRequest updatePhysician);
}
