package com.jsrdev.medapi.infrastructure.database.mysql.entity;

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
    private String stateOrProvince;
    private String municipalityOrDelegation;
    private String country;
    private String city;
    private Integer zipCode;
    private String externalNumber;
    private String internalNumber;
    private String complement;
}
