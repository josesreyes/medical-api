package com.jsrdev.medapi.infrastructure.database.mysql.mapper;

import com.jsrdev.medapi.domain.model.address.Address;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.AddressEntity;

public class AddressMapper {
    public static Address fromAddressEntityToAddress(AddressEntity addressEntity) {
        return new Address(
                addressEntity.getStreet(),
                addressEntity.getStateOrProvince(),
                addressEntity.getMunicipalityOrDelegation(),
                addressEntity.getCountry(),
                addressEntity.getCity(),
                addressEntity.getZipCode(),
                addressEntity.getExternalNumber(),
                addressEntity.getInternalNumber(),
                addressEntity.getComplement()
        );
    }

    public static AddressEntity fromAddressToAddressEntity(Address address) {
        return new AddressEntity(
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
}
