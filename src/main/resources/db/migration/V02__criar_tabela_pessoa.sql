CREATE TABLE pessoa (
	id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
	nome varchar(50) NOT NULL,
	ativo boolean NOT NULL,
	logradouro varchar(50) default NULL,
	numero smallint(4) default NULL,
	complemento varchar(30) default NULL,
	bairro varchar(30) default NULL,
	cep varchar(8) default NULL,
	cidade varchar(30) default NULL,
	estado varchar(2) default NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) VALUES ('Diorgenes Morais', 1, 'Rua Sárdio', 229, 'Rosina Labanca', '54735200', 'São Lourenço da Mata', 'PE');
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) VALUES ('Arnaldo Rodrigues', 1, 'Rua Águas Marinhas', 299, 'Rosina Labanca', '54735285', 'São Lourenço da Mata', 'PE');
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) VALUES ('Sigismundo Agostinho', 0, 'Rua dos Correios', 80, 'Centro', '54735000', 'São Lourenço da Mata', 'PE');
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) VALUES ('Leandro Nunes', 1, 'Rua Const. Avelino de Sá', 75, 'Vázea Fria', '54740160', 'São Lourenço da Mata', 'PE');
