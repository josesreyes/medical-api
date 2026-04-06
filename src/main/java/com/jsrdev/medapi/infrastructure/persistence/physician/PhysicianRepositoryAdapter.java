package com.jsrdev.medapi.infrastructure.persistence.physician;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.port.out.PhysicianRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PhysicianRepositoryAdapter implements PhysicianRepositoryPort {

    private final PhysicianJpaRepository jpaRepository;
    private final PhysicianPersistenceMapper mapper;

    @Override
    public Physician create(Physician physician) {
        PhysicianEntity saved = jpaRepository.save(mapper.toEntity(physician));
        return mapper.toDomain(saved);
    }

    @Override
    public Physician update(Physician physician) {
        PhysicianEntity saved = jpaRepository.save(mapper.toEntity(physician));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Physician> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.existsByEmail(email.value());
    }

    @Override
    public boolean existsByDocument(String document) {
        return jpaRepository.existsByDocument(document);
    }

    @Override
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
        return jpaRepository.existsByPhoneNumber(phoneNumber.value());
    }

    @Override
    public Page<Physician> findActivePhysicians(Pageable pageable) {
        return jpaRepository.findByIsActiveTrue(pageable).map(mapper::toDomain);
    }

    @Override
    public void deactivate(UUID id) {
        jpaRepository.deactivateById(id);
    }
}
