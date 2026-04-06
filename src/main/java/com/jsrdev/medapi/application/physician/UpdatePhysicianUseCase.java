package com.jsrdev.medapi.application.physician;

import com.jsrdev.medapi.domain.model.physician.Physician;

import java.util.UUID;

public interface UpdatePhysicianUseCase {
    Physician update(UUID id, Physician physician);
}
