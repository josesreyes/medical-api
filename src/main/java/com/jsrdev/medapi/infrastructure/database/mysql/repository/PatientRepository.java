package com.jsrdev.medapi.infrastructure.database.mysql.repository;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {
    boolean existsByEmail(Email email);

    boolean existsByIdentityDocument(String identityDocument);

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
}
