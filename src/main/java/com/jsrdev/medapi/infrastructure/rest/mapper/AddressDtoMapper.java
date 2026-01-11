package com.jsrdev.medapi.infrastructure.rest.mapper;

import com.jsrdev.medapi.domain.model.address.Address;
import com.jsrdev.medapi.infrastructure.rest.dto.AddressRequest;
import com.jsrdev.medapi.infrastructure.rest.dto.AddressResponse;

public class AddressDtoMapper {

    public static AddressResponse fromAddressToAddressResponse(Address address) {
        return new AddressResponse(
                address.getStreet(),
                address.getStateOrProvince(),
                address.getMunicipalityOrDelegation(),
                address.getCountry(),
                address.getCity(),
                address.getZipCode(),
                address.getExternalNumber(),
                address.getInternalNumber(),
                address.getComplement()
        );
    }

    public static Address fromAddresDtoToAddress(AddressRequest address) {
        return new Address(
                address.street(),
                address.stateOrProvince(),
                address.municipalityOrDelegation(),
                address.country(),
                address.city(),
                address.zipCode(),
                address.externalNumber(),
                address.internalNumber(),
                address.complement()
        );
    }
}
