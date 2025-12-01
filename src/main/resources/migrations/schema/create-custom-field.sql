CREATE TABLE IF NOT EXISTS custom_field (
                                            id BIGSERIAL PRIMARY KEY,
                                            code VARCHAR(255) NOT NULL UNIQUE,
                                            min INT NOT NULL,
                                            max INT NOT NULL
);
