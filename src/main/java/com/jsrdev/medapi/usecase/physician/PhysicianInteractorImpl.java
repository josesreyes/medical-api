package com.jsrdev.medapi.usecase.physician;

import com.jsrdev.medapi.domain.exception.PhysicianAlreadyExistsException;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.repository.PhysicianRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PhysicianInteractorImpl implements IPhysicianInteractor {

    private final PhysicianRepositoryPort physicianRepository;

    @Override
    public Physician createPhysician(Physician physician) {

        if (physicianRepository.existsByEmail(physician.getEmail())) {
            throw new PhysicianAlreadyExistsException("Physician with email " + physician.getEmail().value() + " already exists");
        }
        return physicianRepository.create(physician);
    }
}
