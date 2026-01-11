package com.jsrdev.medapi.infrastructure.rest.controller;

import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.infrastructure.rest.dto.CreatePhysicianRequest;
import com.jsrdev.medapi.infrastructure.rest.dto.PhysicianResponse;
import com.jsrdev.medapi.infrastructure.rest.mapper.PhysicianDtoMapper;
import com.jsrdev.medapi.usecase.physician.IPhysicianInteractor;
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

    private final IPhysicianInteractor physicianInteractor;

    @PostMapping
    public ResponseEntity<PhysicianResponse> create(
            @Valid @RequestBody CreatePhysicianRequest request) {

        Physician physician = PhysicianDtoMapper.fromPhysicianRequestToPhysician(request);
        Physician created = physicianInteractor.createPhysician(physician);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PhysicianDtoMapper.fromPhysicianToPhysicianResponse(created));
    }

}
