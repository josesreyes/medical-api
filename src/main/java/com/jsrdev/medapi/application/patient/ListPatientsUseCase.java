package com.jsrdev.medapi.application.patient;

import com.jsrdev.medapi.domain.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListPatientsUseCase {
    Page<Patient> listActive(Pageable pageable);


    //Patient getPatientById(UUID id);
}
