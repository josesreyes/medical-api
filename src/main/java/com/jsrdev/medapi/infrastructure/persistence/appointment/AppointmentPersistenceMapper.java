package com.jsrdev.medapi.infrastructure.persistence.appointment;

import com.jsrdev.medapi.domain.model.appointment.Appointment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppointmentPersistenceMapper {

    public AppointmentEntity toEntity(Appointment domain) {
        UUID id = domain.getId() != null ? domain.getId() : UUID.randomUUID();
        return new AppointmentEntity(
                id,
                domain.getPhysicianId(),
                domain.getPatientId(),
                domain.getDate(),
                domain.getStatus(),
                domain.getCancellationReason(),
                domain.getCancellationDate()
        );
    }

    public Appointment toDomain(AppointmentEntity entity) {
        return Appointment.reconstitute(
                entity.getId(),
                entity.getPhysicianId(),
                entity.getPatientId(),
                entity.getDate(),
                entity.getStatus(),
                entity.getCancellationReason(),
                entity.getCancellationDate()
        );
    }
}
