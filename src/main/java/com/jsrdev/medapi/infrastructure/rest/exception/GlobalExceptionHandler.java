package com.jsrdev.medapi.infrastructure.rest.exception;

import com.jsrdev.medapi.domain.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* ===================== 400 BAD REQUEST ===================== */
    // Validaciones DTO (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        List<FieldErrorValidation> fieldErrors = ex.getFieldErrors()
                .stream()
                .map(FieldErrorValidation::new)
                .toList();

        return ResponseEntity.badRequest().body(
                new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "VALIDATION_ERROR",
                        fieldErrors,
                        request.getRequestURI()
                )
        );
    }

    // JSON mal formado / tipos incorrectos
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleUnreadableMessage(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        // Log completo para desarrolladores
        log.error("Unexpected error at {}: {}", request.getRequestURI(), ex.getMessage(), ex);

        return ResponseEntity.badRequest().body(
                new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "INVALID_REQUEST_BODY",
                        List.of(new FieldErrorValidation("", "Malformed JSON or invalid data type")),
                        request.getRequestURI()
                )
        );
    }

    // Reglas de negocio inválidas (dominio)
    @ExceptionHandler({
            InvalidPhysicianDataException.class,
            InvalidAddressDataException.class,
            ValidationException.class,
            IntegrityValidationException.class
    })
    public ResponseEntity<ApiError> handleBusinessValidation(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.badRequest().body(
                new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "BUSINESS_VALIDATION_ERROR",
                        List.of(new FieldErrorValidation("", ex.getMessage())),
                        request.getRequestURI()
                )
        );
    }

    /* ===================== 404 NOT FOUND ===================== */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(
            EntityNotFoundException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "RESOURCE_NOT_FOUND",
                        List.of(new FieldErrorValidation("", ex.getMessage())),
                        request.getRequestURI()
                )
        );
    }

    /* ===================== 409 CONFLICT ===================== */
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleConflict(
            ResourceAlreadyExistsException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.CONFLICT.value(),
                        ex.getCode(),
                        List.of(
                                new FieldErrorValidation(
                                        ex.getField(),
                                        ex.getMessage()
                                )
                        ),
                        request.getRequestURI()
                )
        );
    }

    /* ===================== 401 UNAUTHORIZED ===================== */
/*
    @ExceptionHandler({ BadCredentialsException.class, AuthenticationException.class })
    public ResponseEntity<ApiError> handleAuthentication(
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.UNAUTHORIZED.value(),
                        "UNAUTHORIZED",
                        List.of(new FieldErrorValidation("", "Authentication failed")),
                        request.getRequestURI()
                )
        );
    }*/

    /* ===================== 403 FORBIDDEN ===================== */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.FORBIDDEN.value(),
                        "ACCESS_DENIED",
                        List.of(new FieldErrorValidation("", "Access denied")),
                        request.getRequestURI()
                )
        );
    }

    /* ===================== 500 INTERNAL SERVER ERROR ===================== */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericError(
            Exception ex,
            HttpServletRequest request
    ) {
        // Log completo para desarrolladores
        log.error("Unexpected error at {}: {}", request.getRequestURI(), ex.getMessage(), ex);

        // Aquí SOLO log, nunca exponer el mensaje real
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "INTERNAL_SERVER_ERROR",
                        List.of(new FieldErrorValidation("", "Unexpected error occurred.")),
                        request.getRequestURI()
                )
        );
    }
}



