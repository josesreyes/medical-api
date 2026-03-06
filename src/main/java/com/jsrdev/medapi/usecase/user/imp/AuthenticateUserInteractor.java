package com.jsrdev.medapi.usecase.user.imp;

import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.user.User;
import com.jsrdev.medapi.domain.repository.UserRepositoryPort;
import com.jsrdev.medapi.infrastructure.security.JwtTokenService;
import com.jsrdev.medapi.usecase.user.AuthenticateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUserInteractor implements AuthenticateUser {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public String execute(String login, String password) {

        var authToken = new UsernamePasswordAuthenticationToken(login, password);

        //var authentication = authenticationManager.authenticate(authToken);
        authenticationManager.authenticate(authToken);

        User user = userRepositoryPort.findByLogin(login).orElseThrow(() -> new EntityNotFoundException("User not found"));

        return jwtTokenService.generateToken(user.getId(), user.getLogin());
    }
}
