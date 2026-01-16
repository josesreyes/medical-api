package com.jsrdev.medapi.infrastructure.database.mysql.adapter;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.repository.PhysicianRepositoryPort;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.PhysicianEntity;
import com.jsrdev.medapi.infrastructure.database.mysql.mapper.PhysicianMapper;
import com.jsrdev.medapi.infrastructure.database.mysql.repository.PhysicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PhysicianRepositoryAdapter implements PhysicianRepositoryPort {

    private final PhysicianRepository physicianRepository;

    @Override
    public Physician create(Physician physician) {
        PhysicianEntity entity = PhysicianMapper.fromPhysicianToPhysicianEntity(physician);
        PhysicianEntity saved = physicianRepository.save(entity);
        return PhysicianMapper.fromPhysicianEntityToPhysician(saved);
    }

    @Override
    public Optional<Physician> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public boolean existsByEmail(Email email) {
        return physicianRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocument(String document) {
        return physicianRepository.existsByDocument(document);
    }

    @Override
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
        return physicianRepository.existsByPhoneNumber(phoneNumber);
    }
}
