package com.jsrdev.medapi.usecase.physician.imp;

import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.repository.PhysicianRepositoryPort;
import com.jsrdev.medapi.usecase.physician.GetActivePhysicians;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetActivePhysiciansInteractor implements GetActivePhysicians {

    private final PhysicianRepositoryPort physicianRepositoryPort;

    public GetActivePhysiciansInteractor(PhysicianRepositoryPort physicianRepositoryPort) {
        this.physicianRepositoryPort = physicianRepositoryPort;
    }

    @Override
    public Page<Physician> getActivePhysicians(int page, int size) {
        validateRequest(page, size);

        Page<Physician> physicians = loadActivePhysicians(page, size);

        validateResult(physicians);

        return physicians;
    }

    @Override
    public Physician getPhysicianById(UUID id) {
        return validateIfExists(id);
    }

    private Physician validateIfExists(UUID id) {
        if (physicianRepositoryPort.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Physician with id " + id + " not found");
        }
        return physicianRepositoryPort.findById(id).get();
    }

    /* ===================== INTENTIONS ===================== */

    private void validateRequest(int page, int size) {
        if (page < 0) {
            throw new ValidationException("Page index must be >= 0");
        }
        if (size <= 0) {
            throw new ValidationException("Page size must be greater than 0");
        }
    }

    private Page<Physician> loadActivePhysicians(int page, int size) {
        return physicianRepositoryPort.findActivePhysicians(
                PageRequest.of(page, size)
        );
    }

    private void validateResult(Page<Physician> physicians) {
        if (physicians.isEmpty()) {
            throw new EntityNotFoundException("No active physicians found");
        }
    }

}
