package com.jsrdev.medapi.domain.exception;

public class EntityNotFoundException extends DomainException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
