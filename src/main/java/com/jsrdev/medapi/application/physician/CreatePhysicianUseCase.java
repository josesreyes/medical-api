package com.jsrdev.medapi.application.physician;

import com.jsrdev.medapi.domain.model.physician.Physician;

public interface CreatePhysicianUseCase {
    Physician create(Physician physician);
}

