package com.jsrdev.medapi.infrastructure.web.auth;

import com.jsrdev.medapi.application.auth.AuthUseCase;
import com.jsrdev.medapi.infrastructure.web.auth.dto.AuthRequest;
import com.jsrdev.medapi.infrastructure.web.auth.dto.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        String token = authUseCase.authenticate(request.login(), request.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
