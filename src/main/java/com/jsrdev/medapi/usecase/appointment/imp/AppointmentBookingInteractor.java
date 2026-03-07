package com.jsrdev.medapi.usecase.appointment.imp;

import com.jsrdev.medapi.domain.model.appointment.Appointment;
import com.jsrdev.medapi.infrastructure.rest.appointment.AppointmentDtoMapper;
import com.jsrdev.medapi.infrastructure.rest.appointment.AppointmentRequest;
import com.jsrdev.medapi.infrastructure.rest.appointment.AppointmentResponse;
import com.jsrdev.medapi.usecase.appointment.ReserveAppointment;
import com.jsrdev.medapi.usecase.patient.GetActivePatients;
import com.jsrdev.medapi.usecase.physician.GetActivePhysicians;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentBookingInteractor implements ReserveAppointment {

    private final GetActivePhysicians getActivePhysicians;
    private final GetActivePatients getActivePatients;

    @Override
    public AppointmentResponse reserve(AppointmentRequest appointmentRequest) {
        var physician = getActivePhysicians.getPhysicianById(appointmentRequest.physicianId());
        var patient = getActivePatients.getPatientById(appointmentRequest.patientId());
        Appointment appointment = AppointmentDtoMapper.toDomain(physician, patient, appointmentRequest.date());
        return null;
    }
}
