package com.jsrdev.medapi.infrastructure.rest.controller;

import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.infrastructure.rest.physician.CreatePhysicianRequest;
import com.jsrdev.medapi.infrastructure.rest.physician.PhysicianResponse;
import com.jsrdev.medapi.infrastructure.rest.physician.PhysicianDtoMapper;
import com.jsrdev.medapi.usecase.physician.create.CreatePhysicianUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/physicians")
@RequiredArgsConstructor
public class PhysicianController {

    private final CreatePhysicianUseCase createPhysicianUseCase;

    @PostMapping
    public ResponseEntity<PhysicianResponse> create(
            @Valid @RequestBody CreatePhysicianRequest request) {

        Physician physician = PhysicianDtoMapper.fromPhysicianRequestToPhysician(request);
        Physician created = createPhysicianUseCase.execute(physician);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PhysicianDtoMapper.fromPhysicianToPhysicianResponse(created));
    }
}

