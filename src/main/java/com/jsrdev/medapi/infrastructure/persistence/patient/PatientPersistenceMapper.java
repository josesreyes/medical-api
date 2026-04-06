package com.jsrdev.medapi.infrastructure.persistence.patient;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.infrastructure.persistence.common.AddressEmbeddable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PatientPersistenceMapper {
    public PatientEntity toEntity(Patient domain) {
        PatientEntity entity = new PatientEntity();
        entity.setId(domain.getUuid() != null ? domain.getUuid() : UUID.randomUUID());
        entity.setName(domain.getName());
        entity.setAvatar(domain.getAvatar());
        entity.setEmail(domain.getEmail().value());
        entity.setIdentityDocument(domain.getIdentityDocument());
        entity.setPhoneNumber(domain.getPhoneNumber().value());
        entity.setIsActive(domain.getIsActive());
        entity.setAddress(AddressEmbeddable.from(domain.getAddress()));
        return entity;
    }

    public Patient toDomain(PatientEntity entity) {
        return new Patient(
                entity.getId(),
                entity.getName(),
                entity.getAvatar(),
                Email.of(entity.getEmail()),
                entity.getIdentityDocument(),
                PhoneNumber.of(entity.getPhoneNumber()),
                entity.getIsActive(),
                entity.getAddress().toDomain()
        );
    }
}
