package com.jsrdev.medapi.infrastructure.persistence.patient;

import com.jsrdev.medapi.infrastructure.persistence.common.AddressEmbeddable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientEntity {
    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "avatar", length = 500)
    private String avatar;

    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String email;

    @Column(name = "identity_document", nullable = false, unique = true, length = 50)
    private String identityDocument;

    @Column(name = "phone_number", nullable = false, unique = true, length = 30)
    private String phoneNumber;

    @Column(name = "is_active")
    private Boolean isActive;

    @Embedded
    private AddressEmbeddable address;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
