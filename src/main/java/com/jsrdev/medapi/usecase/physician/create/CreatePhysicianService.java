package com.jsrdev.medapi.usecase.physician.create;

import com.jsrdev.medapi.domain.exception.PhysicianAlreadyExistsException;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.repository.PhysicianRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePhysicianService implements CreatePhysicianUseCase {

    private final PhysicianRepositoryPort physicianRepository;

    @Override
    public Physician execute(Physician physician) {

        if (physicianRepository.existsByEmail(physician.getEmail())) {
            throw new PhysicianAlreadyExistsException(
                    "Physician with email " + physician.getEmail().value() + " already exists"
            );
        }

        return physicianRepository.create(physician);
    }
}

