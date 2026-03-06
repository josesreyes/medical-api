package com.jsrdev.medapi.infrastructure.database.mysql.mapper;

import com.jsrdev.medapi.domain.model.user.User;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getIsActive(),
                userEntity.getProfiles().stream().map(ProfileMapper::toDomain).toList()
        );
    }
}
