CREATE TABLE appointments (
    id                         BINARY(16)   NOT NULL,
    physician_id               BINARY(16)   NOT NULL,
    patient_id                 BINARY(16)   NOT NULL,
    date                       DATETIME     NOT NULL,
    cancellation_reason        VARCHAR(150),
    cancellation_date          DATETIME,

    PRIMARY KEY (id),
    CONSTRAINT fk_appointments_patient_id       FOREIGN KEY (patient_id)    REFERENCES patients(id),
    CONSTRAINT fk_appointments_physician_id     FOREIGN KEY (physician_id)  REFERENCES physicians(id)
)