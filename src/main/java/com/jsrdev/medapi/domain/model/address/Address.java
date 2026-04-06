package com.jsrdev.medapi.domain.model.address;

import com.jsrdev.medapi.domain.exception.InvalidAddressDataException;
import lombok.Getter;

@Getter
public final class Address {

    private final String street;
    private final String stateOrProvince;
    private final String municipalityOrDelegation;
    private final String country;
    private final String city;
    private final String zipCode;
    private final String externalNumber;
    private final String internalNumber;
    private final String complement;

    public Address(
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
        if (street == null || street.isBlank())
            throw new InvalidAddressDataException("Street required");
        if (stateOrProvince == null || stateOrProvince.isBlank())
            throw new InvalidAddressDataException("State or province required");
        if (municipalityOrDelegation == null || municipalityOrDelegation.isBlank())
            throw new InvalidAddressDataException("Municipality or delegation required");
        if (country == null || country.isBlank())
            throw new InvalidAddressDataException("Country required");
        if (city == null || city.isBlank())
            throw new InvalidAddressDataException("City required");
        if (zipCode == null || zipCode.isBlank())
            throw new InvalidAddressDataException("ZipCode required");
        if (externalNumber == null || externalNumber.isBlank())
            throw new InvalidAddressDataException("ExternalNumber required");

        this.street = street;
        this.stateOrProvince = stateOrProvince;
        this.municipalityOrDelegation = municipalityOrDelegation;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.externalNumber = externalNumber;
        this.internalNumber = internalNumber;
        this.complement = complement;
    }
}
