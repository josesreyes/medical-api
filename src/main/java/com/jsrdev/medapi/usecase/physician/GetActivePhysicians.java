package com.jsrdev.medapi.usecase.physician;

import com.jsrdev.medapi.domain.model.physician.Physician;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface GetActivePhysicians {
    Page<Physician> getActivePhysicians(int page, int size);

    Physician getPhysicianById(UUID id);
}
