package com.jsrdev.medapi.infrastructure.web.physician.dto;

import com.jsrdev.medapi.domain.model.address.Address;

public record AddressResponse(
        String street,
        String stateOrProvince,
        String municipalityOrDelegation,
        String country,
        String city,
        String zipCode,
        String externalNumber,
        String internalNumber,
        String complement
) {
    public static AddressResponse from(Address a) {
        return new AddressResponse(
                a.getStreet(), a.getStateOrProvince(), a.getMunicipalityOrDelegation(),
                a.getCountry(), a.getCity(), a.getZipCode(),
                a.getExternalNumber(), a.getInternalNumber(), a.getComplement()
        );
    }
}
