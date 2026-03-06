package com.jsrdev.medapi.infrastructure.database.mysql.mapper;

import com.jsrdev.medapi.domain.model.profile.Profile;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.ProfileEntity;

public class ProfileMapper {

    public static Profile toDomain(ProfileEntity profileEntity) {
        return new Profile(
                profileEntity.getId(),
                profileEntity.getName(),
                profileEntity.getIsActive()
        );
    }
}
