package com.jsrdev.medapi.domain.model.physician;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.exception.InvalidPhysicianDataException;
import com.jsrdev.medapi.domain.model.address.Address;

import java.util.UUID;

public final class Physician {
    UUID uuid;
    String name;
    String avatar;
    Email email;
    String document;
    PhoneNumber phoneNumber;
    Specialty specialty;
    Address address;

    public Physician(
            UUID uuid,
            String name,
            String avatar,
            Email email,
            String document,
            PhoneNumber phoneNumber,
            Specialty specialty,
            Address address
    ) {
        if (name == null || name.isBlank()) throw new InvalidPhysicianDataException("Name required");
        if (document == null || document.isBlank()) throw new InvalidPhysicianDataException("Document required");
        if (address == null) throw new InvalidPhysicianDataException("Address required");

        this.uuid = uuid;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
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

    public String getDocument() {
        return document;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public Address getAddress() {
        return address;
    }
}
