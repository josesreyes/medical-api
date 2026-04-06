package com.jsrdev.medapi.infrastructure.persistence.user;

import com.jsrdev.medapi.domain.model.user.User;
import com.jsrdev.medapi.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository jpaRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public Optional<User> findByLogin(String login) {
        return jpaRepository.findByLogin(login).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void save(User user) {
        jpaRepository.save(mapper.toEntity(user));
    }
}
