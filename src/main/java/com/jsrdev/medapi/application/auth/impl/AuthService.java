package com.jsrdev.medapi.application.auth.impl;

import com.jsrdev.medapi.application.auth.AuthUseCase;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.exception.InvalidCredentialsException;
import com.jsrdev.medapi.domain.model.user.User;
import com.jsrdev.medapi.domain.port.out.JwtProviderPort;
import com.jsrdev.medapi.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort userRepository;
    private final JwtProviderPort jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String authenticate(String login, String rawPassword) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + login));

        if (!user.isActive())
            throw new InvalidCredentialsException("User account is not active");

        if (!passwordEncoder.matches(rawPassword, user.getPassword()))
            throw new InvalidCredentialsException("Invalid credentials");

        return jwtProvider.generateToken(user.getId(), user.getLogin());
    }
}
