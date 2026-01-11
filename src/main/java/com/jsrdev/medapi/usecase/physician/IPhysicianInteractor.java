package com.jsrdev.medapi.usecase.physician;

import com.jsrdev.medapi.domain.model.physician.Physician;

public interface IPhysicianInteractor {
    Physician createPhysician(Physician physician);
}
