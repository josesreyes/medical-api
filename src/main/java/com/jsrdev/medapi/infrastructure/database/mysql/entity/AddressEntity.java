package com.jsrdev.medapi.infrastructure.database.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AddressEntity {
    private String street;
    @Column(name = "state_or_province")
    private String stateOrProvince;
    @Column(name = "municipality_or_delegation")
    private String municipalityOrDelegation;
    private String country;
    private String city;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "external_number")
    private String externalNumber;
    @Column(name = "internal_number")
    private String internalNumber;
    private String complement;

    public void update(
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
        if (street != null) this.street = street;
        if (stateOrProvince != null) this.stateOrProvince = stateOrProvince;
        if (municipalityOrDelegation != null) this.municipalityOrDelegation = municipalityOrDelegation;
        if (country != null) this.country = country;
        if (city != null) this.city = city;
        if (zipCode != null) this.zipCode = zipCode;
        if (externalNumber != null) this.externalNumber = externalNumber;
        if (internalNumber != null) this.internalNumber = internalNumber;
        if (complement != null) this.complement = complement;
    }
}
