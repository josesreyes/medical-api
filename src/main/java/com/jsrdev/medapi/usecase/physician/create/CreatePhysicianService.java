package com.jsrdev.medapi.usecase.physician.create;

import com.jsrdev.medapi.domain.exception.PhysicianAlreadyExistsException;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.repository.PhysicianRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePhysicianService implements CreatePhysicianUseCase {

    private final PhysicianRepositoryPort physicianRepository;

    @Transactional
    @Override
    public Physician execute(Physician physician) {

        ensurePhysicianDoesNotExist(physician);

        return physicianRepository.create(physician);
    }

    private void ensurePhysicianDoesNotExist(Physician physician) {
        if (physicianRepository.existsByEmail(physician.getEmail())) {
            throw new PhysicianAlreadyExistsException("Email already registered");
        }
        if (physicianRepository.existsByDocument(physician.getDocument())) {
            throw new PhysicianAlreadyExistsException("Document already registered");
        }
        if (physicianRepository.existsByPhoneNumber(physician.getPhoneNumber())) {
            throw new PhysicianAlreadyExistsException("Phone number already registered");
        }
    }

}

