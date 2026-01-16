package com.jsrdev.medapi.infrastructure.database.mysql.adapter;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.repository.PatientRepositoryPort;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.PatientEntity;
import com.jsrdev.medapi.infrastructure.database.mysql.mapper.PatientMapper;
import com.jsrdev.medapi.infrastructure.database.mysql.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
