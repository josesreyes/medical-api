package com.jsrdev.medapi.infrastructure.persistence.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "login", nullable = false, length = 200)
    private String login;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_profiles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<ProfileEntity> profiles;
}
