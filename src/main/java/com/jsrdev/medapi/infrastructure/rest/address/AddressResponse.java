package com.jsrdev.medapi.infrastructure.rest.address;

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
}
