package com.jsrdev.medapi.infrastructure.rest.controller;

import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.infrastructure.rest.patient.CreatePatientRequest;
import com.jsrdev.medapi.infrastructure.rest.patient.PatientDtoMapper;
import com.jsrdev.medapi.infrastructure.rest.patient.PatientResponse;
import com.jsrdev.medapi.usecase.patient.CreatePatient;
import com.jsrdev.medapi.usecase.patient.GetActivePatients;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final CreatePatient createPatient;
    private final GetActivePatients getActivePatients;

    @PostMapping
    public ResponseEntity<PatientResponse> create(
            @Valid @RequestBody CreatePatientRequest request) {

        Patient patient = PatientDtoMapper.fromPatientRequestToPatient(request);
        Patient created = createPatient.execute(patient);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PatientDtoMapper.fromPatientToPatientResponse(created));
    }

    @GetMapping
    public Page<PatientResponse> getActivePatients(
            //@PageableDefault(size = 10, sort = {"name"}) Pageable pageable
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return getActivePatients.getActivePatients(page, size)
                .map(PatientDtoMapper::fromPatientToPatientResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable UUID id) {

        Patient patient = getActivePatients.getPatientById(id);
        PatientResponse patientResponse = PatientDtoMapper.fromPatientToPatientResponse(patient);

        return ResponseEntity.ok(patientResponse);
    }
}
