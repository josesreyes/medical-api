package com.jsrdev.medapi.infrastructure.persistence.physician;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PhysicianJpaRepository extends JpaRepository<PhysicianEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByDocument(String document);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<PhysicianEntity> findByEmail(String email);

    Page<PhysicianEntity> findByIsActiveTrue(Pageable pageable);

    @Modifying
    @Query("UPDATE PhysicianEntity p SET p.isActive = false WHERE p.id = :id")
    void deactivateById(UUID id);
}
