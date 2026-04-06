package com.jsrdev.medapi.application.auth;

public interface AuthUseCase {
    String authenticate(String login, String password);
}
