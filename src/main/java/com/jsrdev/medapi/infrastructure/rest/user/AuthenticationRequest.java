package com.jsrdev.medapi.infrastructure.rest.user;

public record AuthenticationRequest(
        String login,
        String password
) {}
