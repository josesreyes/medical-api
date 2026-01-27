package com.jsrdev.medapi.domain.repository;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.physician.Physician;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PhysicianRepositoryPort {

    Physician create(Physician physician);

    Optional<Physician> findById(UUID id);

    boolean existsByEmail(Email email);

    boolean existsByDocument(String document);

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);

    Page<Physician> findActivePhysicians(Pageable pageable);
}
