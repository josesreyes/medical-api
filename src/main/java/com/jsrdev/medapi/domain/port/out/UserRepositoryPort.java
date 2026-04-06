package com.jsrdev.medapi.domain.port.out;

import com.jsrdev.medapi.domain.model.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    Optional<User> findByLogin(String login);
    Optional<User> findById(UUID id);
    void save(User user);
}
