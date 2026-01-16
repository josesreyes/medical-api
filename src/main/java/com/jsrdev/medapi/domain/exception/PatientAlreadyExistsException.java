package com.jsrdev.medapi.domain.exception;

public class PatientAlreadyExistsException extends ResourceAlreadyExistsException {

    public PatientAlreadyExistsException(String field, String value) {
        super(
                field,
                value,
                field + " '" + value + "' already exists"
        );
    }
}
