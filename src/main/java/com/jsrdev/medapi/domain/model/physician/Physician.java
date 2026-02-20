package com.jsrdev.medapi.domain.model.physician;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.exception.InvalidPhysicianDataException;
import com.jsrdev.medapi.domain.model.address.Address;
import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public final class Physician {
    UUID uuid;
    @Setter
    String name;
    @Setter
    String avatar;
    Email email;
    String document;
    @Setter
    PhoneNumber phoneNumber;
    Specialty specialty;
    Boolean isActive;
    Address address;

    public Physician(
            UUID uuid,
            String name,
            String avatar,
            Email email,
            String document,
            PhoneNumber phoneNumber,
            Specialty specialty,
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
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
        this.isActive = isActive;
        this.address = address;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        if (isActive /*== PhysicianStatus.ACTIVE*/) {
            throw new ValidationException("Already active");
        }

        this.isActive = true;
        //this.status = PhysicianStatus.ACTIVE;
    }
}
