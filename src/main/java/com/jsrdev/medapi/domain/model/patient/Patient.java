package com.jsrdev.medapi.domain.model.patient;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.exception.InvalidPhysicianDataException;
import com.jsrdev.medapi.domain.model.address.Address;

import java.util.UUID;

public final class Patient {
    UUID uuid;
    String name;
    String avatar;
    Email email;
    String identityDocument;
    PhoneNumber phoneNumber;
    Boolean isActive;
    Address address;

    public Patient(
            UUID uuid,
            String name,
            String avatar,
            Email email,
            String document,
            PhoneNumber phoneNumber,
            Boolean isActive,
            Address address
    ) {
        if (name == null || name.isBlank()) throw new InvalidPhysicianDataException("Name required");
        if (document == null || document.isBlank()) throw new InvalidPhysicianDataException("Document required");
        if (address == null) throw new InvalidPhysicianDataException("Address required");

        this.uuid = uuid;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.identityDocument = document;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.address = address;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public Email getEmail() {
        return email;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }
}
