package com.jsrdev.medapi.infrastructure.persistence.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true, length = 150)
    private String name;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
