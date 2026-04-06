package com.jsrdev.medapi.infrastructure.web.patient.dto;

import com.jsrdev.medapi.domain.model.address.Address;
import com.jsrdev.medapi.domain.model.patient.Patient;

import java.util.UUID;

public record PatientResponse(
        UUID id,
        String name,
        String avatar,
        String email,
        String identityDocument,
        String phoneNumber,
        Boolean isActive,
        PatientAddressResponse address
) {
    public static PatientResponse from(Patient p) {
        return new PatientResponse(
                p.getUuid(),
                p.getName(),
                p.getAvatar(),
                p.getEmail().value(),
                p.getIdentityDocument(),
                p.getPhoneNumber().value(),
                p.getIsActive(),
                PatientAddressResponse.from(p.getAddress())
        );
    }

    public record PatientAddressResponse(
            String street, String stateOrProvince, String municipalityOrDelegation,
            String country, String city, String zipCode,
            String externalNumber, String internalNumber, String complement
    ) {
        public static PatientAddressResponse from(Address a) {
            return new PatientAddressResponse(
                    a.getStreet(), a.getStateOrProvince(), a.getMunicipalityOrDelegation(),
                    a.getCountry(), a.getCity(), a.getZipCode(),
                    a.getExternalNumber(), a.getInternalNumber(), a.getComplement()
            );
        }
    }
}
