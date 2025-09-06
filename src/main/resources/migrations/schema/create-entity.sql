CREATE TABLE IF NOT EXISTS entity (
                                         id BIGSERIAL PRIMARY KEY,
                                         name VARCHAR(255) NOT NULL,
                                         number VARCHAR(255) NOT NULL,
                                         properties JSONB,
                                         entity_type_id BIGINT NOT NULL,
                                         CONSTRAINT fk_my_entity_entity_type FOREIGN KEY (entity_type_id)
                                         REFERENCES entity_type (id) ON DELETE CASCADE
);
