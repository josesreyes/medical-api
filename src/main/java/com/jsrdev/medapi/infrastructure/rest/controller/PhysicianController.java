package com.jsrdev.medapi.infrastructure.rest.controller;

import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.infrastructure.rest.physician.CreatePhysicianRequest;
import com.jsrdev.medapi.infrastructure.rest.physician.PhysicianDtoMapper;
import com.jsrdev.medapi.infrastructure.rest.physician.PhysicianResponse;
import com.jsrdev.medapi.infrastructure.rest.physician.UpdatePhysicianRequest;
import com.jsrdev.medapi.usecase.physician.CreatePhysician;
import com.jsrdev.medapi.usecase.physician.DeactivatePhysician;
import com.jsrdev.medapi.usecase.physician.GetActivePhysicians;
import com.jsrdev.medapi.usecase.physician.UpdatePhysician;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/physicians")
@RequiredArgsConstructor
public class PhysicianController {

    private final CreatePhysician createPhysician;
    private final GetActivePhysicians getActivePhysicians;
    private final UpdatePhysician updatePhysician;
    private final DeactivatePhysician deactivatePhysician;

    @PostMapping
    public ResponseEntity<PhysicianResponse> create(
            @Valid @RequestBody CreatePhysicianRequest request,
            UriComponentsBuilder uriBuilder
    ) {

        Physician physician = PhysicianDtoMapper.fromPhysicianRequestToPhysician(request);
        Physician created = createPhysician.execute(physician);

        URI uri = uriBuilder.path("/physicians/{id}").buildAndExpand(created.getUuid()).toUri();

        return ResponseEntity.created(uri)/*.status(HttpStatus.CREATED)*/
                .body(PhysicianDtoMapper.fromPhysicianToPhysicianResponse(created));
    }

    @GetMapping
    public ResponseEntity<Page<PhysicianResponse>> getActivePhysicians(
            //@PageableDefault(size = 10, sort = {"name"}) Pageable pageable
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var responsePage = getActivePhysicians.getActivePhysicians(page, size)
                .map(PhysicianDtoMapper::fromPhysicianToPhysicianResponse);

        return ResponseEntity.ok(responsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhysicianResponse> getPhysicianById(@PathVariable UUID id) {

        Physician physician = getActivePhysicians.getPhysicianById(id);
        PhysicianResponse physicianResponse = PhysicianDtoMapper.fromPhysicianToPhysicianResponse(physician);

        return ResponseEntity.ok(physicianResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhysicianResponse> updatePhysician(@PathVariable UUID id, @Valid @RequestBody UpdatePhysicianRequest updateRequest) {
        var physician = updatePhysician.execute(id, updateRequest);
        return ResponseEntity.ok(PhysicianDtoMapper.fromPhysicianToPhysicianResponse(physician));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivatePhysician(@PathVariable UUID id) {
        deactivatePhysician.execute(id);
        return ResponseEntity.noContent().build();
    }
}

