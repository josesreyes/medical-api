package com.jsrdev.medapi.infrastructure.database.mysql.adapter;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.address.Address;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.repository.PatientRepositoryPort;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.AddressEntity;
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
        return patientRepository.findById(id)
                .map(PatientMapper::fromPatientEntityToPatient);
    }

    @Override
    public Patient update(Patient patient) {
        PatientEntity entity = patientRepository.findById(patient.getUuid())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        mapDomainToExistingEntity(patient, entity);

        return PatientMapper.fromPatientEntityToPatient(entity);
    }

    private void mapDomainToExistingEntity(Patient patient, PatientEntity entity) {
        entity.setName(patient.getName());
        entity.setAvatar(patient.getAvatar());
        entity.setPhoneNumber(patient.getPhoneNumber());
        entity.setIsActive(patient.getIsActive());
        // update address
        mapAddress(patient.getAddress(), entity.getAddress());
    }

    private void mapAddress(Address domainAddress, AddressEntity entityAddress) {
        entityAddress.setStreet(domainAddress.getStreet());
        entityAddress.setStateOrProvince(domainAddress.getStateOrProvince());
        entityAddress.setMunicipalityOrDelegation(domainAddress.getMunicipalityOrDelegation());
        entityAddress.setCountry(domainAddress.getCountry());
        entityAddress.setCity(domainAddress.getCity());
        entityAddress.setZipCode(domainAddress.getZipCode());
        entityAddress.setExternalNumber(domainAddress.getExternalNumber());
        entityAddress.setInternalNumber(domainAddress.getInternalNumber());
        entityAddress.setComplement(domainAddress.getComplement());
    }
}
