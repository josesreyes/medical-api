package com.jsrdev.medapi.domain.model.profile;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Profile {
    private UUID id;
    private String name;
    private Boolean active;

    public Profile(UUID id, String name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }
}
