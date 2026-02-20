package com.jsrdev.medapi.usecase.patient.imp;

import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.repository.PatientRepositoryPort;
import com.jsrdev.medapi.infrastructure.rest.patient.UpdatePatientRequest;
import com.jsrdev.medapi.usecase.patient.UpdatePatient;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdatePatientInteractor implements UpdatePatient {

    private final PatientRepositoryPort patientRepositoryPort;

    public UpdatePatientInteractor(PatientRepositoryPort patientRepositoryPort) {
        this.patientRepositoryPort = patientRepositoryPort;
    }

    @Transactional
    @Override
    public Patient execute(UUID id, UpdatePatientRequest updateRequest) {
        validateRequest(id, updateRequest);

        Patient patient = loadPatient(id);

        ensurePatientIsActive(patient);

        applyChanges(patient, updateRequest);

        return savePatient(patient);
    }

    /* ===================== INTENTIONS ===================== */
    private void validateRequest(UUID id, UpdatePatientRequest updateRequest) {
        if (id == null) {
            throw new ValidationException("Patient id is required");
        }

        if (updateRequest == null) {
            throw new ValidationException("Update data is required");
        }
    }

    private Patient loadPatient(UUID id) {
        return patientRepositoryPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
    }

    private void ensurePatientIsActive(Patient patient) {
        if (!patient.getIsActive()) {
            throw new ValidationException("Inactive physicians cannot be updated");
        }
    }

    private void applyChanges(Patient patient, UpdatePatientRequest update) {
        if (update.name() != null) patient.setName(update.name());
        if (update.avatar() != null) patient.setAvatar(update.avatar());
        if (update.phoneNumber() != null) patient.setPhoneNumber(PhoneNumber.of(update.phoneNumber()));

        if (update.address() != null) {
            var addressRequest = update.address();
            var domainAddress = patient.getAddress();

            if (addressRequest.street() != null) domainAddress.setStreet(addressRequest.street());
            if (addressRequest.stateOrProvince() != null)
                domainAddress.setStateOrProvince(addressRequest.stateOrProvince());
            if (addressRequest.municipalityOrDelegation() != null)
                domainAddress.setMunicipalityOrDelegation(addressRequest.municipalityOrDelegation());
            if (addressRequest.country() != null) domainAddress.setCountry(addressRequest.country());
            if (addressRequest.city() != null) domainAddress.setCity(addressRequest.city());
            if (addressRequest.zipCode() != null) domainAddress.setZipCode(addressRequest.zipCode());
            if (addressRequest.externalNumber() != null)
                domainAddress.setExternalNumber(addressRequest.externalNumber());
            if (addressRequest.internalNumber() != null)
                domainAddress.setInternalNumber(addressRequest.internalNumber());
            if (addressRequest.complement() != null) domainAddress.setComplement(addressRequest.complement());
        }
    }

    private Patient savePatient(Patient patient) {
        return patientRepositoryPort.update(patient);
    }
}
