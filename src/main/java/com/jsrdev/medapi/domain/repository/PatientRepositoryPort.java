package com.jsrdev.medapi.domain.repository;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.patient.Patient;

public interface PatientRepositoryPort {
    Patient create(Patient patient);

    boolean existsByEmail(Email email);

    boolean existsByIdentityDocument(String identityDocument);

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
}
