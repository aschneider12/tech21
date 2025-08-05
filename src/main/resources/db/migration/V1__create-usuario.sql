-- cria tabela usuario
CREATE TABLE IF NOT EXISTS usuario (
	id serial NOT NULL UNIQUE,
	nome varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	login varchar(255) NOT NULL UNIQUE,
	senha varchar(255) NOT NULL,
	data_ultima_alteracao timestamp NOT NULL,
	endereco_id BIGINT NULL,
	PRIMARY KEY (id)
);

-- insert two default values
INSERT INTO usuario (nome, email, login, senha, data_ultima_alteracao)
VALUES ('Clark Quenti - Pass: Kent@123', 'clark@quenti.com', 'kent','$2a$12$z3/i8T6bALXCqiBgaDV3h.ngP6vVgOfKsLnnEVlDjePLPSJg7yYsu', now());

INSERT INTO usuario (nome, email, login, senha, data_ultima_alteracao)
VALUES ('Clark Cold - Pass: Cold@123', 'clark@cold.com', 'cold','$2a$12$rIG3auWZ4M58BEkFpbB3HuKNiufIcO7cBVYiyDhPRrM1P2OxffOTu', now());
