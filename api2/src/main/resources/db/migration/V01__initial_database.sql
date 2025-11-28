CREATE TABLE IF NOT EXISTS modelos (
    id SERIAL PRIMARY KEY,
    codigo_modelo INT NOT NULL,
    nome_modelo VARCHAR(200) NOT NULL,
    codigo_marca INT NOT NULL,
    nome_marca VARCHAR(200) NOT NULL
);
