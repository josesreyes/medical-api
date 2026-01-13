package com.jsrdev.medapi.usecase.physician.create;

import com.jsrdev.medapi.domain.model.physician.Physician;

public interface CreatePhysicianUseCase {
    Physician execute(Physician physician);
}

