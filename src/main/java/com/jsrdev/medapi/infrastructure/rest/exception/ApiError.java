package com.jsrdev.medapi.infrastructure.rest.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        LocalDateTime timestamp,
        int status,
        String error,
        List<FieldErrorValidation> messages,
        String path
) {}

