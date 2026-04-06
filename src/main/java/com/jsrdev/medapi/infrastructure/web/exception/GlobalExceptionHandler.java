package com.jsrdev.medapi.infrastructure.web.exception;

import com.jsrdev.medapi.domain.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ── 400 — DTO inválido (@Valid) ─────────────────────────────────────────
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest req) {

        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest().body(
                ErrorResponse.of(400, "Bad Request", "VALIDATION_ERROR", message, req.getRequestURI()));
    }

    // ── 400 — JSON malformado ────────────────────────────────────────────────
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleUnreadable(
            HttpMessageNotReadableException ex, HttpServletRequest req) {

        return ResponseEntity.badRequest().body(
                ErrorResponse.of(400, "Bad Request", "MALFORMED_JSON",
                        "Request body is missing or malformed", req.getRequestURI()));
    }

    // ── 400 — datos de dominio inválidos ────────────────────────────────────
    @ExceptionHandler({
            InvalidPhysicianDataException.class,
            InvalidAddressDataException.class,
            InvalidAppointmentDataException.class
    })
    public ResponseEntity<ErrorResponse> handleInvalidDomainData(
            DomainException ex, HttpServletRequest req) {

        return ResponseEntity.badRequest().body(
                ErrorResponse.of(400, "Bad Request", ex.getCode(), ex.getMessage(), req.getRequestURI()));
    }

    // ── 400 — IllegalArgumentException (enum parse) ─────────────────────────
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, HttpServletRequest req) {

        return ResponseEntity.badRequest().body(
                ErrorResponse.of(400, "Bad Request", "INVALID_ARGUMENT", ex.getMessage(), req.getRequestURI()));
    }

    // ── 401 — credenciales inválidas ────────────────────────────────────────
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(
            InvalidCredentialsException ex, HttpServletRequest req) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ErrorResponse.of(401, "Unauthorized", ex.getCode(), ex.getMessage(), req.getRequestURI()));
    }

    // ── 404 — recurso no encontrado ─────────────────────────────────────────
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            EntityNotFoundException ex, HttpServletRequest req) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.of(404, "Not Found", ex.getCode(), ex.getMessage(), req.getRequestURI()));
    }

    // ── 409 — duplicado ─────────────────────────────────────────────────────
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists(
            ResourceAlreadyExistsException ex, HttpServletRequest req) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.of(409, "Conflict", ex.getCode(), ex.getMessage(), req.getRequestURI()));
    }

    // ── 422 — regla de integridad de negocio ────────────────────────────────
    @ExceptionHandler(IntegrityValidationException.class)
    public ResponseEntity<ErrorResponse> handleIntegrity(
            IntegrityValidationException ex, HttpServletRequest req) {

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                ErrorResponse.of(422, "Unprocessable Entity", ex.getCode(), ex.getMessage(), req.getRequestURI()));
    }

    // ── 500 — fallback ──────────────────────────────────────────────────────
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex, HttpServletRequest req) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.of(500, "Internal Server Error", "UNEXPECTED_ERROR",
                        "An unexpected error occurred", req.getRequestURI()));
    }
}
