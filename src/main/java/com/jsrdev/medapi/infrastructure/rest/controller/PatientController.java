package com.jsrdev.medapi.infrastructure.rest.controller;

import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.infrastructure.rest.patient.CreatePatientRequest;
import com.jsrdev.medapi.infrastructure.rest.patient.PatientDtoMapper;
import com.jsrdev.medapi.infrastructure.rest.patient.PatientResponse;
import com.jsrdev.medapi.usecase.patient.create.CreatePatientUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final CreatePatientUseCase createPatientUseCase;

    @PostMapping
    public ResponseEntity<PatientResponse> create(
            @Valid @RequestBody CreatePatientRequest request) {

        Patient patient = PatientDtoMapper.fromPatientRequestToPatient(request);
        Patient created = createPatientUseCase.execute(patient);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PatientDtoMapper.fromPatientToPatientResponse(created));
    }
}
