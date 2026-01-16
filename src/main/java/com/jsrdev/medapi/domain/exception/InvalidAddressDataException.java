package com.jsrdev.medapi.domain.exception;

public class InvalidAddressDataException extends DomainException{
    public InvalidAddressDataException(String message) {
        super("INVALID_ADDRESS_DATA", message);
    }
}