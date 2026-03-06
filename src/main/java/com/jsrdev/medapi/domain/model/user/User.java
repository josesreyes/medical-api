package com.jsrdev.medapi.domain.model.user;

import com.jsrdev.medapi.domain.model.profile.Profile;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class User {
    private UUID id;
    private String name;
    private String login;
    private String password;
    private Boolean active;
    private List<Profile> profiles;

    public User(UUID id, String name, String login, String password, Boolean active, List<Profile> profiles) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.active = active;
        this.profiles = profiles;
    }

    public boolean isActive() {
        return active;
    }

    public boolean hasProfile(String profileName) {
        return profiles.stream()
                .anyMatch(p -> p.getName().equals(profileName));
    }
}
