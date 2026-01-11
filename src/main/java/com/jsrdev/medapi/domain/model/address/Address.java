package com.jsrdev.medapi.domain.model.address;

public final class Address {

    String street;
    String stateOrProvince;
    String municipalityOrDelegation;
    String country;
    String city;
    Integer zipCode;
    String externalNumber;
    String internalNumber;
    String complement;

    public Address(
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
        if (street == null || street.isBlank()) throw new IllegalArgumentException("Street required");
        if (stateOrProvince == null || stateOrProvince.isBlank()) throw new IllegalArgumentException("State or province required");
        if (municipalityOrDelegation == null || municipalityOrDelegation.isBlank()) throw new IllegalArgumentException("Municipality or delegation required");
        if (country == null || country.isBlank()) throw new IllegalArgumentException("Country required");
        if (city == null || city.isBlank()) throw new IllegalArgumentException("City required");
        if (zipCode == null) throw new IllegalArgumentException("ZipCode required");
        if (externalNumber == null || externalNumber.isBlank()) throw new IllegalArgumentException("ExternalNumber required");

        this.street = street;
        this.city = city;
        this.stateOrProvince = stateOrProvince;
        this.municipalityOrDelegation = municipalityOrDelegation;
        this.country = country;
        this.zipCode = zipCode;
        this.externalNumber = externalNumber;
        this.internalNumber = internalNumber;
        this.complement = complement;
    }
}
