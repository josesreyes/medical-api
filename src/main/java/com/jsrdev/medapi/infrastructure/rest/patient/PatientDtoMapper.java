package com.jsrdev.medapi.infrastructure.rest.patient;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.infrastructure.rest.address.AddressDtoMapper;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class PatientDtoMapper {

    public static Patient fromPatientRequestToPatient(CreatePatientRequest request) {
        return new Patient(
                null,
                Jsoup.clean(request.name(), Safelist.none()),
                Jsoup.clean(request.avatar(), Safelist.none()),
                Email.of(request.email()),
                request.identityDocument(),
                PhoneNumber.of(request.phoneNumber()),
                null,
                AddressDtoMapper.fromAddresDtoToAddress(request.address())
        );
    }

    public static PatientResponse fromPatientToPatientResponse(Patient patient) {
        return new PatientResponse(
                patient.getUuid(),
                patient.getName(),
                patient.getAvatar(),
                patient.getEmail().value(),
                patient.getIdentityDocument(),
                patient.getPhoneNumber().value(),
                AddressDtoMapper.fromAddressToAddressResponse(patient.getAddress())
        );
    }
}
