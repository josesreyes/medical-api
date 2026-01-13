package com.jsrdev.medapi.infrastructure.rest.exception;

import org.springframework.validation.FieldError;

public record FieldErrorValidation(String field, String message) {
    public FieldErrorValidation(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
