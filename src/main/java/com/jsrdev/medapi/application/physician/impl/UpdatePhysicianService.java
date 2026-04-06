package com.jsrdev.medapi.application.physician.impl;

import com.jsrdev.medapi.application.physician.UpdatePhysicianUseCase;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.port.out.PhysicianRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdatePhysicianService implements UpdatePhysicianUseCase {

    private final PhysicianRepositoryPort physicianRepository;

    @Override
    @Transactional
    public Physician update(UUID id, Physician incoming) {
        Physician existing = physicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Physician not found: " + id));

        if (incoming.getName() != null) existing.setName(incoming.getName());
        if (incoming.getAvatar() != null) existing.setAvatar(incoming.getAvatar());
        if (incoming.getPhoneNumber() != null) existing.setPhoneNumber(incoming.getPhoneNumber());
        if (incoming.getAddress() != null) existing.setAddress(incoming.getAddress());

        return physicianRepository.update(existing);
    }
}
