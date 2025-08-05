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
	usuario_id bigint NOT NULL,
	tipo_usuario varchar(255) NOT NULL,
	PRIMARY KEY (usuario_id, tipo_usuario),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);


ALTER TABLE usuario ADD CONSTRAINT USUARIO_fk3 FOREIGN KEY (endereco_id) REFERENCES endereco(id);

ALTER TABLE restaurante ADD CONSTRAINT RESTAURANTE_fk2 FOREIGN KEY (endereco_id) REFERENCES endereco(id);

ALTER TABLE restaurante ADD CONSTRAINT RESTAURANTE_fk5 FOREIGN KEY (usuario_id) REFERENCES usuario(id);

ALTER TABLE item ADD CONSTRAINT ITEM_fk5 FOREIGN KEY (restaurante_id) REFERENCES restaurante(id);