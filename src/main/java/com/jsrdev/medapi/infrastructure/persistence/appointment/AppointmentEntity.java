package com.jsrdev.medapi.infrastructure.persistence.appointment;

import com.jsrdev.medapi.domain.common.AppointmentStatus;
import com.jsrdev.medapi.domain.common.CancellationReason;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID id;

    @Column(name = "physician_id", columnDefinition = "BINARY(16)", nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID physicianId;

    @Column(name = "patient_id", columnDefinition = "BINARY(16)", nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID patientId;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private AppointmentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "cancellation_reason", length = 100)
    private CancellationReason cancellationReason;

    @Column(name = "cancellation_date")
    private LocalDateTime cancellationDate;
}
