package com.jsrdev.medapi.domain.exception;

public class IntegrityValidationException extends DomainException {

    public IntegrityValidationException(String message) {
        super("INTEGRITY_VALIDATION_ERROR", message);
    }
}
