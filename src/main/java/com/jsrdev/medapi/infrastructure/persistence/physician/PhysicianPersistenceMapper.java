package com.jsrdev.medapi.infrastructure.persistence.physician;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.infrastructure.persistence.common.AddressEmbeddable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PhysicianPersistenceMapper {
    public PhysicianEntity toEntity(Physician domain) {
        PhysicianEntity entity = new PhysicianEntity();
        entity.setId(domain.getUuid() != null ? domain.getUuid() : UUID.randomUUID());
        entity.setName(domain.getName());
        entity.setAvatar(domain.getAvatar());
        entity.setEmail(domain.getEmail().value());
        entity.setDocument(domain.getDocument());
        entity.setPhoneNumber(domain.getPhoneNumber().value());
        entity.setSpecialty(domain.getSpecialty());
        entity.setIsActive(domain.getIsActive());
        entity.setAddress(AddressEmbeddable.from(domain.getAddress()));
        return entity;
    }

    public Physician toDomain(PhysicianEntity entity) {
        return new Physician(
                entity.getId(),
                entity.getName(),
                entity.getAvatar(),
                Email.of(entity.getEmail()),
                entity.getDocument(),
                PhoneNumber.of(entity.getPhoneNumber()),
                entity.getSpecialty(),
                entity.getIsActive(),
                entity.getAddress().toDomain()
        );
    }
}
