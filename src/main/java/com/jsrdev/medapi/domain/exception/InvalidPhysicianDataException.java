package com.jsrdev.medapi.domain.exception;

public class InvalidPhysicianDataException extends DomainException {
    public InvalidPhysicianDataException(String message) {
        super("INVALID_PHYSICIAN_DATA", message);
    }
}
