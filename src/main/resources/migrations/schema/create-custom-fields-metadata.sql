CREATE TABLE IF NOT EXISTS custom_fields_metadata (
                                                      id BIGSERIAL PRIMARY KEY,
                                                      custom_field_id BIGINT NOT NULL UNIQUE,
                                                      type VARCHAR(50) NOT NULL,
                                                      CONSTRAINT fk_metadata_custom_field FOREIGN KEY (custom_field_id)
                                                          REFERENCES custom_field (id) ON DELETE CASCADE
);
