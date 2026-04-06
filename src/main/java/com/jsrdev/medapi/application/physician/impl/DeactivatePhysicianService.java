package com.jsrdev.medapi.application.physician.impl;

import com.jsrdev.medapi.application.physician.DeactivatePhysicianUseCase;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.port.out.PhysicianRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeactivatePhysicianService implements DeactivatePhysicianUseCase {

    private final PhysicianRepositoryPort physicianRepository;

    @Override
    @Transactional
    public void deactivate(UUID id) {
        physicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Physician not found: " + id));
        physicianRepository.deactivate(id);
    }
}

