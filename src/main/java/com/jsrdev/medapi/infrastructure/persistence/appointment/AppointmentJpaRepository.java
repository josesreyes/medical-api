package com.jsrdev.medapi.infrastructure.persistence.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, UUID> {

    boolean existsByPhysicianIdAndDate(UUID physicianId, LocalDateTime date);

    List<AppointmentEntity> findByPhysicianIdAndDateBetween(
            UUID physicianId, LocalDateTime start, LocalDateTime end);

    List<AppointmentEntity> findByPatientIdAndDateBetween(
            UUID patientId, LocalDateTime start, LocalDateTime end);

    List<AppointmentEntity> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
