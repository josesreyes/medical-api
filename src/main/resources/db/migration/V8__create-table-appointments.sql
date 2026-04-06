CREATE TABLE appointments (
    id                         BINARY(16)   NOT NULL,
    physician_id               BINARY(16)   NOT NULL,
    patient_id                 BINARY(16)   NOT NULL,
    date                       DATETIME     NOT NULL,
    status                     VARCHAR(50)  NOT NULL    DEFAULT 'SCHEDULED',
    cancellation_reason        VARCHAR(100),
    cancellation_date          DATETIME,

    PRIMARY KEY (id),
    CONSTRAINT fk_appointments_patient_id       FOREIGN KEY (patient_id)    REFERENCES patients(id),
    CONSTRAINT fk_appointments_physician_id     FOREIGN KEY (physician_id)  REFERENCES physicians(id),

    /* evita doble booking */
    CONSTRAINT uk_appointments_unique               UNIQUE (physician_id, date)
);

-- performance
/* Optimiza busqueda como SELECT * FROM appointments WHERE physician_id = ? */
CREATE INDEX idx_appointments_physician_id      ON appointments(physician_id);
CREATE INDEX idx_appointments_patient_id        ON appointments(patient_id);
CREATE INDEX idx_appointments_date              ON appointments(date);