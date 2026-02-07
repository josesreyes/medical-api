package com.jsrdev.medapi.infrastructure.database.mysql.adapter;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.repository.PatientRepositoryPort;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.PatientEntity;
import com.jsrdev.medapi.infrastructure.database.mysql.mapper.PatientMapper;
import com.jsrdev.medapi.infrastructure.database.mysql.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PatientRepositoryAdapter implements PatientRepositoryPort {
    private final PatientRepository patientRepository;

    @Override
    public Patient create(Patient patient) {
        PatientEntity entity = PatientMapper.fromPatientToPatientEntity(patient);
        PatientEntity saved = patientRepository.save(entity);
        return PatientMapper.fromPatientEntityToPatient(saved);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return patientRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByIdentityDocument(String identityDocument) {
        return patientRepository.existsByIdentityDocument(identityDocument);
    }

    @Override
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
        return patientRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Page<Patient> findActivePatients(Pageable pageable) {
        return patientRepository.findByIsActiveTrue(pageable)
                .map(PatientMapper::fromPatientEntityToPatient);
    }

    @Override
    public Optional<Patient> findById(UUID id) {
        return patientRepository.findByIdAndIsActiveTrue(id)
                .map(PatientMapper::fromPatientEntityToPatient);
    }
}
