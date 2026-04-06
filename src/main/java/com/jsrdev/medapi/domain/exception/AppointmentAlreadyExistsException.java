package com.jsrdev.medapi.domain.exception;

public class AppointmentAlreadyExistsException extends ResourceAlreadyExistsException {

    public AppointmentAlreadyExistsException(String field, String value) {
        super(field, value, "Physician already has an appointment at: '" + value + "'");
    }
}
