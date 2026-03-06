package com.jsrdev.medapi.infrastructure.database.mysql.adapter;

import com.jsrdev.medapi.domain.model.user.User;
import com.jsrdev.medapi.domain.repository.UserRepositoryPort;
import com.jsrdev.medapi.infrastructure.database.mysql.mapper.UserMapper;
import com.jsrdev.medapi.infrastructure.database.mysql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository
                .findByLogin(login)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void save(User user) {

    }
}
