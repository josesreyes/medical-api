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
    private Integer zipCode;
    @Column(name = "external_number")
    private String externalNumber;
    @Column(name = "internal_number")
    private String internalNumber;
    private String complement;
}
