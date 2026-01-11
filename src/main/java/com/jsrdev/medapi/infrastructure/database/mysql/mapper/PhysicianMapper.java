package com.jsrdev.medapi.infrastructure.database.mysql.mapper;

import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.PhysicianEntity;

public class PhysicianMapper {

    public static PhysicianEntity fromPhysicianToPhysicianEntity(Physician physician) {
        return new PhysicianEntity(
                null,
                physician.getName(),
                physician.getAvatar(),
                physician.getEmail(),
                physician.getDocument(),
                physician.getPhoneNumber(),
                physician.getSpecialty(),
                AddressMapper.fromAddressToAddressEntity(physician.getAddress())
        );
    }

    public static Physician fromPhysicianEntityToPhysician(PhysicianEntity physicianEntity) {
        return new Physician(
                physicianEntity.getId(),
                physicianEntity.getName(),
                physicianEntity.getAvatar(),
                physicianEntity.getEmail(),
                physicianEntity.getDocument(),
                physicianEntity.getPhoneNumber(),
                physicianEntity.getSpecialty(),
                AddressMapper.fromAddressEntityToAddress(physicianEntity.getAddress())
        );
    }
}
