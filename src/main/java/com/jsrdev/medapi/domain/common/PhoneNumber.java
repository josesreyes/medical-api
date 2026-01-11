package com.jsrdev.medapi.domain.common;

import com.jsrdev.medapi.domain.exception.InvalidPhysicianDataException;

public final class PhoneNumber {

    private final String value;

    private PhoneNumber(String value) {
        this.value = value;
    }

    public static PhoneNumber of(String value) {
        if (value == null || value.isBlank()) throw new InvalidPhysicianDataException("Phone number is required");
        if (!value.matches("\\d{7,15}"))  throw new InvalidPhysicianDataException("Invalid phone number");

        return new PhoneNumber(value);
    }

    public String value() {
        return value;
    }
}

/*
* public record PhoneNumber(String value) {

    public PhoneNumber {
        if (value == null || !value.matches("\\+?[0-9]{10,15}")) {
            throw new InvalidPhysicianDataException("Invalid phone number");
        }
    }

    public static PhoneNumber of(String value) {
        return new PhoneNumber(value);
    }
}

* */
