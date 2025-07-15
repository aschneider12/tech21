-- cria tabela usuario
CREATE TABLE IF NOT EXISTS usuario (
  id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  nome TEXT NOT NULL,
  email TEXT NOT NULL,
  login TEXT NOT NULL,
  senha TEXT NOT NULL,
  data_ultima_alteracao TIMESTAMP NULL,
  tipo_usuario TEXT NOT NULL
);

-- insert two default values
INSERT INTO usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario)
VALUES ('Clark Quenti', 'clark@quenti.com', 'teste','admin', now(), 'DONO');

INSERT INTO usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario)
VALUES ('Clark Cold', 'clark@cold.com', 'outroteste','admin', now(), 'CLIENTE');
