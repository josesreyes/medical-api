package com.jsrdev.medapi.usecase.physician.imp;

import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.repository.PhysicianRepositoryPort;
import com.jsrdev.medapi.infrastructure.rest.physician.UpdatePhysicianRequest;
import com.jsrdev.medapi.usecase.physician.UpdatePhysician;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdatePhysicianInteractor implements UpdatePhysician {
    private final PhysicianRepositoryPort physicianRepositoryPort;

    @Transactional
    @Override
    public Physician execute(UUID id, UpdatePhysicianRequest updatePhysician) {
        validateRequest(id, updatePhysician);

        Physician physician = loadPhysician(id);

        ensurePhysicianIsActive(physician);

        applyChanges(physician, updatePhysician);

        return savePhysician(physician);
    }

    /* ===================== INTENTIONS ===================== */

    private void validateRequest(UUID id, UpdatePhysicianRequest updateRequest) {
        if (id == null) {
            throw new ValidationException("Physician id is required");
        }

        if (updateRequest == null) {
            throw new ValidationException("Update data is required");
        }
    }

    private Physician loadPhysician(UUID id) {
        return physicianRepositoryPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Physician not found"));
    }

    private void ensurePhysicianIsActive(Physician physician) {
        if (!physician.getIsActive()) {
            throw new ValidationException("Inactive physicians cannot be updated");
        }
    }

    private void applyChanges(Physician physician, UpdatePhysicianRequest update) {
        if (update.name() != null) physician.setName(update.name());
        if (update.avatar() != null) physician.setAvatar(update.avatar());
        if (update.phoneNumber() != null) physician.setPhoneNumber(PhoneNumber.of(update.phoneNumber()));

        if (update.address() != null) {
            var addressRequest = update.address();
            var domainAddress = physician.getAddress();

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

    private Physician savePhysician(Physician physician) {
        return physicianRepositoryPort.update(physician);
    }
}
