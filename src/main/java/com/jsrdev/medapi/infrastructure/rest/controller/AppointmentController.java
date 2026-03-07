package com.jsrdev.medapi.infrastructure.rest.controller;

import com.jsrdev.medapi.infrastructure.rest.appointment.AppointmentRequest;
import com.jsrdev.medapi.infrastructure.rest.appointment.AppointmentResponse;
import com.jsrdev.medapi.usecase.appointment.ReserveAppointment;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final ReserveAppointment reserveAppointment;

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(
            @Valid @RequestBody AppointmentRequest appointmentRequest,
            UriComponentsBuilder uriBuilder
    ) {
        AppointmentResponse appointmentResponse = reserveAppointment
                .reserve(appointmentRequest);

        URI uri = uriBuilder.path("/appointments/{id}").buildAndExpand(appointmentResponse.id()).toUri();

        return ResponseEntity.created(uri).body(appointmentResponse);
    }
}
