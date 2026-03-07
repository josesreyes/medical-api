package com.jsrdev.medapi.usecase.appointment;

import com.jsrdev.medapi.infrastructure.rest.appointment.AppointmentRequest;
import com.jsrdev.medapi.infrastructure.rest.appointment.AppointmentResponse;

public interface ReserveAppointment {
    AppointmentResponse reserve(AppointmentRequest appointmentRequest);
}
