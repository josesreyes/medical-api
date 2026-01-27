package com.jsrdev.medapi.infrastructure.rest.controller;

import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.infrastructure.rest.physician.CreatePhysicianRequest;
import com.jsrdev.medapi.infrastructure.rest.physician.PhysicianDtoMapper;
import com.jsrdev.medapi.infrastructure.rest.physician.PhysicianResponse;
import com.jsrdev.medapi.usecase.physician.CreatePhysician;
import com.jsrdev.medapi.usecase.physician.GetActivePhysicians;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/physicians")
@RequiredArgsConstructor
public class PhysicianController {

    private final CreatePhysician createPhysician;
    private final GetActivePhysicians getActivePhysicians;

    @PostMapping
    public ResponseEntity<PhysicianResponse> create(
            @Valid @RequestBody CreatePhysicianRequest request) {

        Physician physician = PhysicianDtoMapper.fromPhysicianRequestToPhysician(request);
        Physician created = createPhysician.execute(physician);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PhysicianDtoMapper.fromPhysicianToPhysicianResponse(created));
    }

    @GetMapping
    public Page<PhysicianResponse> getActivePhysicians(
            //@PageableDefault(size = 10, sort = {"name"}) Pageable pageable
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return getActivePhysicians.getActivePhysicians(page, size)
                .map(PhysicianDtoMapper::fromPhysicianToPhysicianResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhysicianResponse> getPhysicianById(@PathVariable UUID id) {

        Physician physician = getActivePhysicians.getPhysicianById(id);
        PhysicianResponse physicianResponse = PhysicianDtoMapper.fromPhysicianToPhysicianResponse(physician);

        return ResponseEntity.ok(physicianResponse);
    }
}

