package com.jsrdev.medapi.infrastructure.web.physician;

import com.jsrdev.medapi.application.physician.CreatePhysicianUseCase;
import com.jsrdev.medapi.application.physician.DeactivatePhysicianUseCase;
import com.jsrdev.medapi.application.physician.ListPhysiciansUseCase;
import com.jsrdev.medapi.application.physician.UpdatePhysicianUseCase;
import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.address.Address;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.model.physician.Specialty;
import com.jsrdev.medapi.infrastructure.web.physician.dto.AddressRequest;
import com.jsrdev.medapi.infrastructure.web.physician.dto.CreatePhysicianRequest;
import com.jsrdev.medapi.infrastructure.web.physician.dto.PhysicianResponse;
import com.jsrdev.medapi.infrastructure.web.physician.dto.UpdatePhysicianRequest;
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
@RequestMapping("/physicians")
@RequiredArgsConstructor
public class PhysicianController {

    private final CreatePhysicianUseCase createUseCase;
    private final UpdatePhysicianUseCase updateUseCase;
    private final DeactivatePhysicianUseCase deactivateUseCase;
    private final ListPhysiciansUseCase listUseCase;

    @PostMapping
    public ResponseEntity<PhysicianResponse> create(
            @RequestBody @Valid CreatePhysicianRequest req,
            UriComponentsBuilder uriBuilder
    ) {
        Physician physician = createUseCase.create(toDomain(req));
        URI location = uriBuilder.path("/physicians/{id}")
                .buildAndExpand(physician.getUuid()).toUri();
        return ResponseEntity.created(location).body(PhysicianResponse.from(physician));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhysicianResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdatePhysicianRequest req
    ) {
        Physician patch = patchDomain(req);
        return ResponseEntity.ok(PhysicianResponse.from(updateUseCase.update(id, patch)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        deactivateUseCase.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<PhysicianResponse>> listActive(
            @PageableDefault(size = 15, sort = "name") Pageable pageable
    ) {
        return ResponseEntity.ok(listUseCase.listActive(pageable).map(PhysicianResponse::from));
    }

    // ── helpers ─────────────────────────────────────────────────────────────

    private Physician toDomain(CreatePhysicianRequest req) {
        AddressRequest a = req.address();
        Address address = new Address(
                a.street(), a.stateOrProvince(), a.municipalityOrDelegation(),
                a.country(), a.city(), a.zipCode(),
                a.externalNumber(), a.internalNumber(), a.complement()
        );
        return new Physician(
                null,
                req.name(),
                req.avatar(),
                Email.of(req.email()),
                req.document(),
                PhoneNumber.of(req.phoneNumber()),
                Specialty.parseSpecialty(req.specialty()),
                true,
                address
        );
    }

    private Physician patchDomain(UpdatePhysicianRequest req) {
        Address address = null;
        if (req.address() != null) {
            AddressRequest a = req.address();
            address = new Address(
                    a.street(), a.stateOrProvince(), a.municipalityOrDelegation(),
                    a.country(), a.city(), a.zipCode(),
                    a.externalNumber(), a.internalNumber(), a.complement()
            );
        }
        return new Physician(
                null,
                req.name(),
                req.avatar(),
                null,
                null,
                req.phoneNumber() != null ? PhoneNumber.of(req.phoneNumber()) : null,
                null,
                null,
                address
        );
    }
}
