CREATE TABLE IF NOT EXISTS "USUARIO" (
	"id" serial NOT NULL UNIQUE,
	"nome" varchar(255) NOT NULL,
	"email" varchar(255) NOT NULL,
	"login" varchar(255) NOT NULL UNIQUE,
	"senha" varchar(255) NOT NULL,
	"data_ultima_alteracao" timestamp with time zone NOT NULL,
	"tipo_usuario" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "RESTAURANTE" (
	"id" serial NOT NULL UNIQUE,
	"nome" varchar(255) NOT NULL,
	"endereco_id" bigint,
	"tipo_cozinha" varchar(255) NOT NULL,
	"horario_funcionamento" varchar(255),
	"usuario_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "ENDERECO" (
	"id" serial NOT NULL UNIQUE,
	"rua" varchar(255) NOT NULL,
	"numero" varchar(255),
	"cidade" varchar(255) NOT NULL,
	"estado" varchar(255) NOT NULL,
	"cep" varchar(255),
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "ITEM" (
	"id" serial NOT NULL UNIQUE,
	"nome" varchar(255) NOT NULL UNIQUE,
	"descricao" varchar(255),
	"preco" numeric(10,0) NOT NULL,
	"tipo_venda" varchar(25) NOT NULL,
	"restaurante_id" bigint NOT NULL,
	"path_foto" varchar(255),
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "USUARIO_PERFIL" (
	"id" serial NOT NULL UNIQUE,
	"usuario_id" bigint NOT NULL,
	"tipo_usuario" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

ALTER TABLE "USUARIO"
ADD COLUMN "endereco_id" bigint;

ALTER TABLE "USUARIO" ADD CONSTRAINT "USUARIO_fk3" FOREIGN KEY ("endereco_id") REFERENCES "ENDERECO"("id");
ALTER TABLE "RESTAURANTE" ADD CONSTRAINT "RESTAURANTE_fk2" FOREIGN KEY ("endereco_id") REFERENCES "ENDERECO"("id");

ALTER TABLE "RESTAURANTE" ADD CONSTRAINT "RESTAURANTE_fk5" FOREIGN KEY ("usuario_id") REFERENCES "USUARIO"("id");

ALTER TABLE "ITEM" ADD CONSTRAINT "ITEM_fk5" FOREIGN KEY ("restaurante_id") REFERENCES "RESTAURANTE"("id");
ALTER TABLE "USUARIO_PERFIL" ADD CONSTRAINT "USUARIO_PERFIL_fk1" FOREIGN KEY ("usuario_id") REFERENCES "USUARIO"("id");