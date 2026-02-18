package com.jsrdev.medapi.domain.model.address;

import com.jsrdev.medapi.domain.exception.InvalidAddressDataException;
import com.jsrdev.medapi.infrastructure.rest.address.AddressRequest;

public final class Address {
    String street;
    String stateOrProvince;
    String municipalityOrDelegation;
    String country;
    String city;
    String zipCode;
    String externalNumber;
    String internalNumber;
    String complement;

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
        if (street == null || street.isBlank()) throw new InvalidAddressDataException("Street required");
        if (stateOrProvince == null || stateOrProvince.isBlank())
            throw new InvalidAddressDataException("State or province required");
        if (municipalityOrDelegation == null || municipalityOrDelegation.isBlank())
            throw new InvalidAddressDataException("Municipality or delegation required");
        if (country == null || country.isBlank()) throw new InvalidAddressDataException("Country required");
        if (city == null || city.isBlank()) throw new InvalidAddressDataException("City required");
        if (zipCode == null) throw new InvalidAddressDataException("ZipCode required");
        if (externalNumber == null || externalNumber.isBlank())
            throw new InvalidAddressDataException("ExternalNumber required");
        if (complement == null || complement.isBlank()) throw new InvalidAddressDataException("Complement required");

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

    public String getStreet() {
        return street;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public String getMunicipalityOrDelegation() {
        return municipalityOrDelegation;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getExternalNumber() {
        return externalNumber;
    }

    public String getInternalNumber() {
        return internalNumber;
    }

    public String getComplement() {
        return complement;
    }

    public void update(AddressRequest update) {
        if (update.street() != null) this.street = update.street();
        if (update.stateOrProvince() != null) this.stateOrProvince = update.stateOrProvince();
        if (update.municipalityOrDelegation() != null)
            this.municipalityOrDelegation = update.municipalityOrDelegation();
        if (update.city() != null) this.city = update.city();
        if (update.zipCode() != null) this.zipCode = update.zipCode();
        if (update.country() != null) this.country = update.country();
        if (update.externalNumber() != null) this.externalNumber = update.externalNumber();
        if (update.internalNumber() != null) this.internalNumber = update.internalNumber();
        if (update.complement() != null) this.complement = update.complement();
    }
}
