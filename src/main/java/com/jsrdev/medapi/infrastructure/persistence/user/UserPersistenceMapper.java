package com.jsrdev.medapi.infrastructure.persistence.user;

import com.jsrdev.medapi.domain.model.profile.Profile;
import com.jsrdev.medapi.domain.model.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPersistenceMapper {

    public User toDomain(UserEntity entity) {
        List<Profile> profiles = entity.getProfiles().stream()
                .map(p -> new Profile(p.getId(), p.getName(), p.getIsActive()))
                .toList();

        return new User(
                entity.getId(),
                entity.getName(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getIsActive(),
                profiles
        );
    }

    public UserEntity toEntity(User domain) {
        List<ProfileEntity> profiles = domain.getProfiles().stream()
                .map(p -> new ProfileEntity(p.getId(), p.getName(), p.getActive()))
                .toList();

        return new UserEntity(
                domain.getId(),
                domain.getName(),
                domain.getLogin(),
                domain.getPassword(),
                domain.isActive(),
                profiles
        );
    }
}
