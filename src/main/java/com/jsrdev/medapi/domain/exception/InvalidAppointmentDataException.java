package com.jsrdev.medapi.domain.exception;

public class InvalidAppointmentDataException extends DomainException {

    public InvalidAppointmentDataException(String message) {
        super("INVALID_APPOINTMENT_DATA", message);
    }
}
