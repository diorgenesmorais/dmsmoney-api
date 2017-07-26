CREATE TABLE lancamento (
    id bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    descricao varchar(50) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagto DATE,
    valor DECIMAL(10,2) NOT NULL,
    observacao varchar(100),
    tipo varchar(7) NOT NULL,
    categoria_id bigint(20) UNSIGNED NOT NULL,
    pessoa_id bigint(20) UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento VALUES (0, "Pró-labore", "2017-07-01", null, 2700.0, null, "RECEITA", 5, 1);
