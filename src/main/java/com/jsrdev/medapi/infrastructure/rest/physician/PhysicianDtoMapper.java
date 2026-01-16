package com.jsrdev.medapi.infrastructure.rest.physician;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.model.physician.Specialty;
import com.jsrdev.medapi.infrastructure.rest.address.AddressDtoMapper;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class PhysicianDtoMapper {
    public static PhysicianResponse fromPhysicianToPhysicianResponse(Physician physician) {
        return new PhysicianResponse(
                physician.getUuid(),
                physician.getName(),
                physician.getAvatar(),
                physician.getEmail().value(),
                physician.getDocument(),
                physician.getPhoneNumber().value(),
                physician.getSpecialty(),
                AddressDtoMapper.fromAddressToAddressResponse(physician.getAddress())
        );
    }

    public static Physician fromPhysicianRequestToPhysician(CreatePhysicianRequest request) {
        return new Physician(
                null,
                Jsoup.clean(request.name(), Safelist.none()),
                Jsoup.clean(request.avatar(), Safelist.none()),
                Email.of(request.email()),
                request.document(),
                PhoneNumber.of(request.phoneNumber()),
                Specialty.parseSpecialty(String.valueOf(request.specialty())),
                AddressDtoMapper.fromAddresDtoToAddress(request.address())
        );
    }
}
