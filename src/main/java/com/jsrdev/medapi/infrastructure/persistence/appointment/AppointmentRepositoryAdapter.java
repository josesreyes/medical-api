package com.jsrdev.medapi.infrastructure.persistence.appointment;

import com.jsrdev.medapi.domain.model.appointment.Appointment;
import com.jsrdev.medapi.domain.port.out.AppointmentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryAdapter implements AppointmentRepositoryPort {

    private final AppointmentJpaRepository jpaRepository;
    private final AppointmentPersistenceMapper mapper;

    @Override
    public Appointment reserve(Appointment appointment) {
        AppointmentEntity entity = mapper.toEntity(appointment);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public boolean existByPhysicianAndDate(UUID physicianId, LocalDateTime date) {
        return jpaRepository.existsByPhysicianIdAndDate(physicianId, date);
    }

    @Override
    public Optional<Appointment> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Appointment> findByPhysicianAndDateRange(
            UUID physicianId, LocalDateTime start, LocalDateTime end) {
        return jpaRepository
                .findByPhysicianIdAndDateBetween(physicianId, start, end)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Appointment> findByPatientAndDateRange(
            UUID patientId, LocalDateTime start, LocalDateTime end) {
        return jpaRepository
                .findByPatientIdAndDateBetween(patientId, start, end)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Appointment> findAllByDateRange(LocalDateTime start, LocalDateTime end) {
        return jpaRepository
                .findByDateBetween(start, end)
                .stream().map(mapper::toDomain).toList();
    }
}
