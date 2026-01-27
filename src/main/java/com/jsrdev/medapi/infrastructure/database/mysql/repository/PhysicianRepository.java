package com.jsrdev.medapi.infrastructure.database.mysql.repository;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.PhysicianEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhysicianRepository extends JpaRepository<PhysicianEntity, UUID> {
    boolean existsByEmail(Email email);
    boolean existsByDocument(String document);
    boolean existsByPhoneNumber(PhoneNumber phoneNumber);

    Page<PhysicianEntity> findByIsActiveTrue(Pageable pageable);
}
