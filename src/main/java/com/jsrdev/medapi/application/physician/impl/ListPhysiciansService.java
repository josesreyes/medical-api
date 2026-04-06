package com.jsrdev.medapi.application.physician.impl;

import com.jsrdev.medapi.application.physician.ListPhysiciansUseCase;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.port.out.PhysicianRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListPhysiciansService implements ListPhysiciansUseCase {

    private final PhysicianRepositoryPort physicianRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Physician> listActive(Pageable pageable) {
        return physicianRepository.findActivePhysicians(pageable);
    }
}
