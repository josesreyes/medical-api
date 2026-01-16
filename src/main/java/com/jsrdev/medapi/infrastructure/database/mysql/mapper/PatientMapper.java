package com.jsrdev.medapi.infrastructure.database.mysql.mapper;

import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.PatientEntity;

public class PatientMapper {

    public static PatientEntity fromPatientToPatientEntity(Patient patient) {
        return new PatientEntity(
                patient.getUuid(),
                patient.getName(),
                patient.getAvatar(),
                patient.getEmail(),
                patient.getIdentityDocument(),
                patient.getPhoneNumber(),
                AddressMapper.fromAddressToAddressEntity(patient.getAddress())
        );
    }

    public static Patient fromPatientEntityToPatient(PatientEntity entity) {
        return new Patient(
                entity.getId(),
                entity.getName(),
                entity.getAvatar(),
                entity.getEmail(),
                entity.getIdentityDocument(),
                entity.getPhoneNumber(),
                AddressMapper.fromAddressEntityToAddress(entity.getAddress())
        );
    }
}
