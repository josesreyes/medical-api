package com.jsrdev.medapi.infrastructure.persistence.patient;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.port.out.PatientRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryAdapter implements PatientRepositoryPort {

    private final PatientJpaRepository jpaRepository;
    private final PatientPersistenceMapper mapper;

    @Override
    public Patient create(Patient patient) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(patient)));
    }

    @Override
    public Patient update(Patient patient) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(patient)));
    }

    @Override
    public Optional<Patient> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.existsByEmail(email.value());
    }

    @Override
    public boolean existsByIdentityDocument(String identityDocument) {
        return jpaRepository.existsByIdentityDocument(identityDocument);
    }

    @Override
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
        return jpaRepository.existsByPhoneNumber(phoneNumber.value());
    }

    @Override
    public Page<Patient> findActivePatients(Pageable pageable) {
        return jpaRepository.findByIsActiveTrue(pageable).map(mapper::toDomain);
    }

    @Override
    public void deactivate(UUID id) {
        jpaRepository.deactivateById(id);
    }
}
