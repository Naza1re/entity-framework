ALTER TABLE custom_fields_entity_type
    ADD COLUMN IF NOT EXISTS required BOOLEAN DEFAULT false