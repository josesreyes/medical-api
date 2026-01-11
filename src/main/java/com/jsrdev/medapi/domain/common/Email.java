package com.jsrdev.medapi.domain.common;

import com.jsrdev.medapi.domain.exception.InvalidPhysicianDataException;

import java.util.regex.Pattern;

public final class Email {

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        if (value == null || value.isBlank()) throw new InvalidPhysicianDataException("Email required");
        if (!EMAIL_REGEX.matcher(value).matches()) throw new InvalidPhysicianDataException("Invalid email format");

        return new Email(value.toLowerCase());
    }

    public String value() {
        return value;
    }
}
