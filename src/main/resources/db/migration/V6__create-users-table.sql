CREATE TABLE users (
    id                         BINARY(16)   NOT NULL,
    name                       VARCHAR(150) NOT NULL,
    login                      VARCHAR(200) NOT NULL,
    password                   VARCHAR(255) NOT NULL,
    is_active                  TINYINT      NOT NULL,

    created_at                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
)