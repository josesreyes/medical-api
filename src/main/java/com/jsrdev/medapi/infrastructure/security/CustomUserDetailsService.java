package com.jsrdev.medapi.infrastructure.security;

import com.jsrdev.medapi.infrastructure.database.mysql.entity.UserEntity;
import com.jsrdev.medapi.infrastructure.database.mysql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @NullMarked
    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {

        UserEntity user = userRepository
                .findByLogin(login)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Invalid credentials"));

        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(UUID id) {

        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }
}
