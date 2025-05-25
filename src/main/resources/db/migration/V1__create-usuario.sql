-- Criação da tabela USUARIO
CREATE TABLE usuario (
  id SERIAL PRIMARY KEY,
  nome TEXT NOT NULL,
  email TEXT NOT NULL,
  login TEXT NOT NULL,
  senha TEXT NOT NULL,
  data_ultima_alteracao TIMESTAMP NULL,
  tipo_usuario TEXT NOT NULL
);

-- Inserção de usuários

-- senha: 123456
INSERT INTO usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario)
VALUES (
  'Clark Quenti', 
  'clark@quenti.com', 
  'teste',
  '$2a$10$FLVV/LEJkF4U8T6IZAL9wuIaCk3K/JcGZcYQvh2mA8VmFbdjl70LG', 
  now(), 
  'DONO'
);

-- senha: admin
INSERT INTO usuario (nome, email, login, senha, data_ultima_alteracao, tipo_usuario)
VALUES (
  'Clark Cold', 
  'clark@cold.com', 
  'outroteste',
  '$2a$10$hE1hYzQAwgE0xA2UKvpg2OSvjWP6T8hXYhtquzyCkmrOJ1zY0ZKsq',
  now(), 
  'CLIENTE'
);

