package com.jsrdev.medapi.application.physician;

import com.jsrdev.medapi.domain.model.physician.Physician;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListPhysiciansUseCase {
    Page<Physician> listActive(Pageable pageable);
}
