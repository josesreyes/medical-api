package com.jsrdev.medapi.infrastructure.rest.dto;

public record AddressResponse(
        String street,
        String stateOrProvince,
        String municipalityOrDelegation,
        String country,
        String city,
        Integer zipCode,
        String externalNumber,
        String internalNumber,
        String complement
) {
}
