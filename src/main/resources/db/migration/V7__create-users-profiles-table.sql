CREATE TABLE users_profiles (
    user_id         BINARY(16)   NOT NULL,
    profile_id      BINARY(16)   NOT NULL,

    PRIMARY KEY (user_id, profile_id),
    FOREIGN KEY (user_id)               REFERENCES users (id)       ON DELETE CASCADE,
    FOREIGN KEY (profile_id)            REFERENCES profiles (id)    ON DELETE  CASCADE
);