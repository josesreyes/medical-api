package com.jsrdev.medapi.domain.port.out;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PhysicianAvailabilityPort {

    /** @return true si el médico NO tiene otra cita en esa fecha/hora exacta. */
    boolean isAvailable(UUID physicianId, LocalDateTime date);
}
