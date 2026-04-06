package com.jsrdev.medapi.domain.port.out;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepositoryPort {

    Patient create(Patient patient);

    Patient update(Patient patient);

    Optional<Patient> findById(UUID id);

    boolean existsByEmail(Email email);

    boolean existsByIdentityDocument(String identityDocument);

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);

    Page<Patient> findActivePatients(Pageable pageable);

    void deactivate(UUID id);
}
