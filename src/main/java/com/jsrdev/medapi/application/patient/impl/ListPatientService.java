package com.jsrdev.medapi.application.patient.impl;

import com.jsrdev.medapi.application.patient.ListPatientsUseCase;
import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.port.out.PatientRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListPatientService implements ListPatientsUseCase {

    private final PatientRepositoryPort patientRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Patient> listActive(Pageable pageable) {
        return patientRepository.findActivePatients(pageable);
    }
}
