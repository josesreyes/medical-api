CREATE TABLE profiles (
    id                         BINARY(16)   NOT NULL,
    name                       VARCHAR(150) NOT NULL UNIQUE,
    is_active                  TINYINT      NOT NULL ,

    created_at                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
)