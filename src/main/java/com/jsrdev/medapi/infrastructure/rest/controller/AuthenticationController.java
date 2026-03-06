package com.jsrdev.medapi.infrastructure.rest.controller;

import com.jsrdev.medapi.infrastructure.rest.user.AuthenticationRequest;
import com.jsrdev.medapi.infrastructure.rest.user.TokenResponse;
import com.jsrdev.medapi.usecase.user.AuthenticateUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticateUser authenticateUser;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        String token = authenticateUser.execute(request.login(), request.password());
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/secure")
    public String secureEndpoint() {
        return "Only admin";
    }
}
