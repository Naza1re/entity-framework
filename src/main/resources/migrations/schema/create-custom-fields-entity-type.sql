CREATE TABLE IF NOT EXISTS custom_fields_entity_type (
                                                         id BIGSERIAL PRIMARY KEY,
                                                         custom_field_id BIGINT NOT NULL,
                                                         entity_type_id BIGINT NOT NULL,
                                                         CONSTRAINT fk_cf_to_entity_type_cf FOREIGN KEY (custom_field_id)
                                                             REFERENCES custom_field (id) ON DELETE CASCADE,
                                                         CONSTRAINT fk_cf_to_entity_type_et FOREIGN KEY (entity_type_id)
                                                             REFERENCES entity_type (id) ON DELETE CASCADE
);
