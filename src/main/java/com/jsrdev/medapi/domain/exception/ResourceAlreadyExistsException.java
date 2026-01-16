package com.jsrdev.medapi.domain.exception;

public abstract class ResourceAlreadyExistsException extends DomainException {

    private final String field;
    private final String value;

    protected ResourceAlreadyExistsException(
            String field,
            String value,
            String message
    ) {
        super("RESOURCE_ALREADY_EXISTS", message);
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }
}
