package com.jsrdev.medapi.infrastructure.rest.mapper;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.model.physician.Specialty;
import com.jsrdev.medapi.infrastructure.rest.dto.CreatePhysicianRequest;
import com.jsrdev.medapi.infrastructure.rest.dto.PhysicianResponse;

public class PhysicianDtoMapper {
    public static PhysicianResponse fromPhysicianToPhysicianResponse(Physician physician) {
        return new PhysicianResponse(
                physician.getUuid(),
                physician.getName(),
                physician.getAvatar(),
                physician.getEmail(),
                physician.getDocument(),
                physician.getPhoneNumber(),
                physician.getSpecialty(),
                AddressDtoMapper.fromAddressToAddressResponse(physician.getAddress())
        );
    }

    public static Physician fromPhysicianRequestToPhysician(CreatePhysicianRequest request) {
        return new Physician(
                null,
                request.name(),
                request.avatar(),
                Email.of(request.email()),
                request.document(),
                PhoneNumber.of(request.phoneNumber()),
                Specialty.parseSpecialty(String.valueOf(request.specialty())),
                AddressDtoMapper.fromAddresDtoToAddress(request.address())
        );
    }
}
