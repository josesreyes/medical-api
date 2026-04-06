package com.jsrdev.medapi.infrastructure.web.appointment;

import com.jsrdev.medapi.application.appointment.BookAppointmentUseCase;
import com.jsrdev.medapi.application.appointment.CancelAppointmentUseCase;
import com.jsrdev.medapi.application.appointment.ListAppointmentsUseCase;
import com.jsrdev.medapi.domain.common.CancellationReason;
import com.jsrdev.medapi.domain.model.appointment.Appointment;
import com.jsrdev.medapi.infrastructure.web.appointment.dto.AppointmentResponse;
import com.jsrdev.medapi.infrastructure.web.appointment.dto.BookAppointmentRequest;
import com.jsrdev.medapi.infrastructure.web.appointment.dto.CancelAppointmentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final BookAppointmentUseCase bookUseCase;
    private final CancelAppointmentUseCase cancelUseCase;
    private final ListAppointmentsUseCase listUseCase;

    /**
     * POST /appointments
     * Reserva una nueva consulta. Retorna 201 Created + Location header.
     */
    @PostMapping
    public ResponseEntity<AppointmentResponse> book(
            @RequestBody @Valid BookAppointmentRequest req,
            UriComponentsBuilder uriBuilder
    ) {
        Appointment appointment = bookUseCase.book(req.physicianId(), req.patientId(), req.date());

        URI location = uriBuilder
                .path("/appointments/{id}")
                .buildAndExpand(appointment.getId())
                .toUri();

        return ResponseEntity.created(location).body(AppointmentResponse.from(appointment));
    }

    /**
     * DELETE /appointments/{id}
     * Cancela una cita (soft-delete: status → CANCELLED).
     * El body lleva el motivo de cancelación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentResponse> cancel(
            @PathVariable UUID id,
            @RequestBody @Valid CancelAppointmentRequest req
    ) {
        CancellationReason reason = CancellationReason.parseCancellationReason(req.cancellationReason());
        Appointment appointment  = cancelUseCase.cancel(id, reason);
        return ResponseEntity.ok(AppointmentResponse.from(appointment));
    }

    /**
     * GET /appointments
     * Lista todas las citas en el rango por defecto (±1 mes / +6 meses).
     */
    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> listAll() {
        return ResponseEntity.ok(
                listUseCase.listAll().stream().map(AppointmentResponse::from).toList()
        );
    }

    /**
     * GET /appointments?physicianId={uuid}
     * Lista citas de un médico.
     */
    @GetMapping(params = "physicianId")
    public ResponseEntity<List<AppointmentResponse>> listByPhysician(@RequestParam UUID physicianId) {
        return ResponseEntity.ok(
                listUseCase.listByPhysician(physicianId).stream()
                        .map(AppointmentResponse::from).toList()
        );
    }

    /**
     * GET /appointments?patientId={uuid}
     * Lista citas de un paciente.
     */
    @GetMapping(params = "patientId")
    public ResponseEntity<List<AppointmentResponse>> listByPatient(@RequestParam UUID patientId) {
        return ResponseEntity.ok(
                listUseCase.listByPatient(patientId).stream()
                        .map(AppointmentResponse::from).toList()
        );
    }
}
