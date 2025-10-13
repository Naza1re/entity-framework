
CREATE TABLE IF NOT EXISTS entity_type (
                             id          BIGSERIAL PRIMARY KEY,
                             description VARCHAR NOT NULL,
                             name        VARCHAR(255) NOT NULL,
                             code        VARCHAR(255) NOT NULL
);
