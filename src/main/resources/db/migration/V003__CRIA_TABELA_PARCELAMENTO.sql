CREATE TABLE t_parcelamento (
    id SERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    descricao VARCHAR(20) NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    quantidade_parcelas SMALLINT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE t_parcelamento
    ADD CONSTRAINT fk_parcelamento_cliente
    FOREIGN KEY (cliente_id)
    REFERENCES t_cliente (id);