package com.jsrdev.medapi.infrastructure.rest.controller;

import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.infrastructure.rest.patient.CreatePatientRequest;
import com.jsrdev.medapi.infrastructure.rest.patient.PatientDtoMapper;
import com.jsrdev.medapi.infrastructure.rest.patient.PatientResponse;
import com.jsrdev.medapi.infrastructure.rest.patient.UpdatePatientRequest;
import com.jsrdev.medapi.usecase.patient.CreatePatient;
import com.jsrdev.medapi.usecase.patient.DeactivatePatient;
import com.jsrdev.medapi.usecase.patient.GetActivePatients;
import com.jsrdev.medapi.usecase.patient.UpdatePatient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final CreatePatient createPatient;
    private final GetActivePatients getActivePatients;
    private final UpdatePatient updatePatient;
    private final DeactivatePatient deactivatePatient;

    @PostMapping
    public ResponseEntity<PatientResponse> create(
            @Valid @RequestBody CreatePatientRequest request,
            UriComponentsBuilder uriBuilder
    ) {

        Patient patient = PatientDtoMapper.fromPatientRequestToPatient(request);
        Patient created = createPatient.execute(patient);

        URI uri = uriBuilder.path("/patients/{id}").buildAndExpand(created.getUuid()).toUri();
        PatientResponse response = PatientDtoMapper.fromPatientToPatientResponse(created);

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponse>> getActivePatients(
            //@PageableDefault(size = 10, sort = {"name"}) Pageable pageable
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var responsePage = getActivePatients.getActivePatients(page, size)
                .map(PatientDtoMapper::fromPatientToPatientResponse);

        return ResponseEntity.ok(responsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable UUID id) {

        Patient patient = getActivePatients.getPatientById(id);
        PatientResponse patientResponse = PatientDtoMapper.fromPatientToPatientResponse(patient);

        return ResponseEntity.ok(patientResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable UUID id, @Valid @RequestBody UpdatePatientRequest updateRequest) {
        var patient = updatePatient.execute(id, updateRequest);
        return ResponseEntity.ok(PatientDtoMapper.fromPatientToPatientResponse(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivatePatient(@PathVariable UUID id) {
        deactivatePatient.execute(id);
        return ResponseEntity.noContent().build();
    }
}
