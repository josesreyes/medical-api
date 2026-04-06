package com.jsrdev.medapi.infrastructure.web.patient;

import com.jsrdev.medapi.application.patient.CreatePatientUseCase;
import com.jsrdev.medapi.application.patient.DeactivatePatientUseCase;
import com.jsrdev.medapi.application.patient.ListPatientsUseCase;
import com.jsrdev.medapi.application.patient.UpdatePatientUseCase;
import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.address.Address;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.infrastructure.web.patient.dto.CreatePatientRequest;
import com.jsrdev.medapi.infrastructure.web.patient.dto.PatientAddressRequest;
import com.jsrdev.medapi.infrastructure.web.patient.dto.PatientResponse;
import com.jsrdev.medapi.infrastructure.web.patient.dto.UpdatePatientRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final CreatePatientUseCase createUseCase;
    private final UpdatePatientUseCase updateUseCase;
    private final DeactivatePatientUseCase deactivateUseCase;
    private final ListPatientsUseCase listUseCase;

    @PostMapping
    public ResponseEntity<PatientResponse> create(
            @RequestBody @Valid CreatePatientRequest req,
            UriComponentsBuilder uriBuilder
    ) {
        Patient patient = createUseCase.create(toDomain(req));
        URI location = uriBuilder.path("/patients/{id}")
                .buildAndExpand(patient.getUuid()).toUri();
        return ResponseEntity.created(location).body(PatientResponse.from(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdatePatientRequest req
    ) {
        return ResponseEntity.ok(PatientResponse.from(updateUseCase.update(id, patchDomain(req))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        deactivateUseCase.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponse>> listActive(
            @PageableDefault(size = 20, sort = "name") Pageable pageable
    ) {
        return ResponseEntity.ok(listUseCase.listActive(pageable).map(PatientResponse::from));
    }

    // ── helpers ─────────────────────────────────────────────────────────────

    private Patient toDomain(CreatePatientRequest req) {
        PatientAddressRequest a = req.address();
        Address address = new Address(
                a.street(), a.stateOrProvince(), a.municipalityOrDelegation(),
                a.country(), a.city(), a.zipCode(),
                a.externalNumber(), a.internalNumber(), a.complement()
        );
        return new Patient(null, req.name(), req.avatar(),
                Email.of(req.email()), req.identityDocument(),
                PhoneNumber.of(req.phoneNumber()), true, address);
    }

    private Patient patchDomain(UpdatePatientRequest req) {
        Address address = null;
        if (req.address() != null) {
            PatientAddressRequest a = req.address();
            address = new Address(
                    a.street(), a.stateOrProvince(), a.municipalityOrDelegation(),
                    a.country(), a.city(), a.zipCode(),
                    a.externalNumber(), a.internalNumber(), a.complement()
            );
        }
        return new Patient(null, req.name(), req.avatar(), null, null,
                req.phoneNumber() != null ? PhoneNumber.of(req.phoneNumber()) : null,
                null, address);
    }
}
