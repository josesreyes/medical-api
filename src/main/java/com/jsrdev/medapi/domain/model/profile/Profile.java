package com.jsrdev.medapi.domain.model.profile;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Profile {
    private UUID id;
    private String name;
    private Boolean active;
}
