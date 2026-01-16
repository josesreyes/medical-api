CREATE TABLE physicians (
    id                         BINARY(16) NOT NULL,
    name                       VARCHAR(150) NOT NULL,
    avatar                     VARCHAR(500),
    email                      VARCHAR(200) NOT NULL UNIQUE,
    document                   VARCHAR(50)  NOT NULL UNIQUE,
    phone_number               VARCHAR(30)  NOT NULL UNIQUE,
    specialty                  VARCHAR(50)  NOT NULL,

    -- Address (Embedded)
    street                     VARCHAR(150) NOT NULL,
    state_or_province          VARCHAR(100) NOT NULL,
    municipality_or_delegation VARCHAR(100) NOT NULL,
    city                       VARCHAR(100) NOT NULL,
    zip_code                   VARCHAR(20)  NOT NULL,
    country                    VARCHAR(100) NOT NULL,
    external_number            VARCHAR(20)  NOT NULL,
    internal_number            VARCHAR(20),
    complement                 VARCHAR(255),

    created_at                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT pk_physicians PRIMARY KEY (id)
)