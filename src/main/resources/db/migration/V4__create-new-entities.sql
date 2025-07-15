CREATE TABLE IF NOT EXISTS usuario (
	id serial NOT NULL UNIQUE,
	nome varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	login varchar(255) NOT NULL UNIQUE,
	senha varchar(255) NOT NULL,
	data_ultima_alteracao timestamp with time zone NOT NULL,
	tipo_usuario varchar(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS restaurante (
	id serial NOT NULL UNIQUE,
	nome varchar(255) NOT NULL,
	endereco_id bigint,
	tipo_cozinha varchar(255) NOT NULL,
	horario_funcionamento varchar(255),
	usuario_id bigint NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS endereco (
	id serial NOT NULL UNIQUE,
	rua varchar(255) NOT NULL,
	numero varchar(255),
	cidade varchar(255) NOT NULL,
	estado varchar(255) NOT NULL,
	cep varchar(255),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS item (
	id serial NOT NULL UNIQUE,
	nome varchar(255) NOT NULL UNIQUE,
	descricao varchar(255),
	preco numeric(10,2) NOT NULL,
	tipo_venda varchar(25) NOT NULL,
	restaurante_id bigint NOT NULL,
	path_foto varchar(255),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario_perfil (
	id serial NOT NULL UNIQUE,
	usuario_id bigint NOT NULL,
	tipo_usuario varchar(255) NOT NULL,
	PRIMARY KEY (id)
);

ALTER TABLE usuario ADD COLUMN if not exists endereco_id bigint;

ALTER TABLE usuario ADD CONSTRAINT USUARIO_fk3 FOREIGN KEY (endereco_id) REFERENCES endereco(id);
ALTER TABLE restaurante ADD CONSTRAINT RESTAURANTE_fk2 FOREIGN KEY (endereco_id) REFERENCES endereco(id);

ALTER TABLE restaurante ADD CONSTRAINT RESTAURANTE_fk5 FOREIGN KEY (usuario_id) REFERENCES usuario(id);

ALTER TABLE item ADD CONSTRAINT ITEM_fk5 FOREIGN KEY (restaurante_id) REFERENCES restaurante(id);
ALTER TABLE usuario_perfil ADD CONSTRAINT USUARIO_PERFIL_fk1 FOREIGN KEY (usuario_id) REFERENCES usuario(id);