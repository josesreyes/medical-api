package com.jsrdev.medapi.infrastructure.persistence.common;

import com.jsrdev.medapi.domain.model.address.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressEmbeddable {

    @Column(name = "street", nullable = false, length = 150)
    private String street;

    @Column(name = "state_or_province", nullable = false, length = 100)
    private String stateOrProvince;

    @Column(name = "municipality_or_delegation", nullable = false, length = 100)
    private String municipalityOrDelegation;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "zip_code", nullable = false, length = 20)
    private String zipCode;

    @Column(name = "external_number", nullable = false, length = 20)
    private String externalNumber;

    @Column(name = "internal_number", length = 20)
    private String internalNumber;

    @Column(name = "complement", length = 256)
    private String complement;

    public static AddressEmbeddable from(Address domain) {
        return new AddressEmbeddable(
                domain.getStreet(),
                domain.getStateOrProvince(),
                domain.getMunicipalityOrDelegation(),
                domain.getCountry(),
                domain.getCity(),
                domain.getZipCode(),
                domain.getExternalNumber(),
                domain.getInternalNumber(),
                domain.getComplement()
        );
    }

    public Address toDomain() {
        return new Address(
                street, stateOrProvince, municipalityOrDelegation,
                country, city, zipCode, externalNumber, internalNumber, complement
        );
    }
}
