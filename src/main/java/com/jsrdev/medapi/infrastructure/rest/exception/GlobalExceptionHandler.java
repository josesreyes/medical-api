package com.jsrdev.medapi.infrastructure.rest.exception;

import com.jsrdev.medapi.domain.exception.InvalidAddressDataException;
import com.jsrdev.medapi.domain.exception.InvalidPhysicianDataException;
import com.jsrdev.medapi.domain.exception.PhysicianAlreadyExistsException;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PhysicianAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleConflict(PhysicianAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiError("PHYSICIAN_ALREADY_EXISTS", ex.getMessage()));
    }

    @ExceptionHandler(InvalidPhysicianDataException.class)
    public ResponseEntity<ApiError> handleInvalidData(InvalidPhysicianDataException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ApiError("INVALID_PHYSICIAN_DATA", ex.getMessage()));
    }

    @ExceptionHandler(InvalidAddressDataException.class)
    public ResponseEntity<ApiError> handleInvalidAddressData(InvalidAddressDataException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ApiError("INVALID_PHYSICIAN_DATA", ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleError404(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError("INVALID_PHYSICIAN_DATA", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ApiError("VALIDATION_ERROR",
                        ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage()));
    }
}


