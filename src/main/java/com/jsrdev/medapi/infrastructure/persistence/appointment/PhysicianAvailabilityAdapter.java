package com.jsrdev.medapi.infrastructure.persistence.appointment;

import com.jsrdev.medapi.domain.port.out.PhysicianAvailabilityPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PhysicianAvailabilityAdapter implements PhysicianAvailabilityPort {

    private final AppointmentJpaRepository jpaRepository;

    @Override
    public boolean isAvailable(UUID physicianId, LocalDateTime date) {
        return !jpaRepository.existsByPhysicianIdAndDate(physicianId, date);
    }
}
