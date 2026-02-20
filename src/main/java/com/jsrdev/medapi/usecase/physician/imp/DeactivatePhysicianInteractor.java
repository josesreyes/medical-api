package com.jsrdev.medapi.usecase.physician.imp;

import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.repository.PhysicianRepositoryPort;
import com.jsrdev.medapi.usecase.physician.DeactivatePhysician;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeactivatePhysicianInteractor implements DeactivatePhysician {

    private final PhysicianRepositoryPort physicianRepository;

    public DeactivatePhysicianInteractor(PhysicianRepositoryPort physicianRepository) {
        this.physicianRepository = physicianRepository;
    }

    @Transactional
    @Override
    public void execute(UUID id) {
        Physician physician = loadPhysician(id);

        ensureCanBeDeactivated(physician);

        physician.deactivate();

        physicianRepository.update(physician);
    }

    private Physician loadPhysician(UUID id) {
        return physicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Physician not found"));
    }

    private void ensureCanBeDeactivated(Physician physician) {
        if (!physician.getIsActive()) {
            throw new ValidationException("Physician is already inactive");
        }

        /*
        * Despues validar esto:
        * ensureNoActiveAppointments(physician);
        * ensureNoPendingPatients(physician);
        * */
    }

}

