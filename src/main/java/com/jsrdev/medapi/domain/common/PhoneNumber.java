package com.jsrdev.medapi.domain.common;

public final class PhoneNumber {

    private final String value;

    private PhoneNumber(String value) {
        this.value = value;
    }

    public static PhoneNumber of(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be blank");
        }
        if (!value.matches("\\d{7,15}")) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        return new PhoneNumber(value);
    }

    public String value() {
        return value;
    }
}
