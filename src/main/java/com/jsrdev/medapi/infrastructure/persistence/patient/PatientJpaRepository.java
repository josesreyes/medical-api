package com.jsrdev.medapi.infrastructure.persistence.patient;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PatientJpaRepository extends JpaRepository<PatientEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByIdentityDocument(String identityDocument);

    boolean existsByPhoneNumber(String phoneNumber);

    Page<PatientEntity> findByIsActiveTrue(Pageable pageable);

    @Modifying
    @Query("UPDATE PatientEntity p SET p.isActive = false WHERE p.id = :id")
    void deactivateById(UUID id);
}
