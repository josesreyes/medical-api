package com.jsrdev.medapi.domain.model.user;

import com.jsrdev.medapi.domain.model.profile.Profile;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Boolean active;
    private List<Profile> profiles;
}
