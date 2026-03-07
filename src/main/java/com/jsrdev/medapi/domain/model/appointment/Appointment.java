package com.jsrdev.medapi.domain.model.appointment;

import com.jsrdev.medapi.domain.model.patient.Patient;
import com.jsrdev.medapi.domain.model.physician.Physician;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Appointment {
    private UUID id;

    private Physician physician;

    private Patient patient;

    private LocalDateTime date;

    //private CancellationReason cancellationReason;

    //private LocalDateTime cancellationDate;

    public Appointment(UUID id, Physician physician, Patient patient, LocalDateTime date) {
        this.id = id;
        this.physician = physician;
        this.patient = patient;
        this.date = date;
    }



    /*public void cancel(CancellationReason cancellationReason) {
        this.cancellationReason = cancellationReason;
        this.cancellationDate = LocalDateTime.now();
    } */


}
