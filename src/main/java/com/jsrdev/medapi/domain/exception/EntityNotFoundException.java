package com.jsrdev.medapi.domain.exception;

public class EntityNotFoundException extends DomainException {

    public EntityNotFoundException(String message) {
        super("RESOURCE_NOT_FOUND", message);
    }
}