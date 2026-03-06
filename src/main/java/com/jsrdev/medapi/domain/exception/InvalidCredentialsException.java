package com.jsrdev.medapi.domain.exception;

public class InvalidCredentialsException extends DomainException{
    protected InvalidCredentialsException(String message) {
        super("INVALID_CREDENTIALS", message);
    }
}
