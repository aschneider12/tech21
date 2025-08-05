
-- inserir somente 1 endereço, sera usado para o restaurante
insert into endereco (rua, numero, cidade, estado, cep)
values ('Av. Paulista', 51, 'São Paulo', 'SP', '01.311-000');

-- atualiza o endereco do restaurante e marca o primeiro usuario como dono
insert into restaurante (nome, endereco_id, tipo_cozinha, horario_funcionamento, usuario_id)
values ('Santo Canto', (select id from endereco limit 1), 'COZINHA ORIENTAL', 'Atendimento das 19h as 23h', (select id from usuario limit 1));

-- insere novo endereco, sera usado para todos os usuarios
insert into endereco (rua, numero, cidade, estado, cep)
values ('Morro do Maracujá', 24, 'Rio de Janeiro', 'RJ', '21.922-490.');

update usuario set endereco_id = (select id from endereco order by id desc limit 1 );

insert into usuario_perfil (usuario_id, tipo_usuario)
values ((select id from usuario limit 1), 'DONO');

insert into usuario_perfil (usuario_id, tipo_usuario)
values ((select id from usuario limit 1), 'CLIENTE');

insert into item (nome, descricao, preco, tipo_venda, restaurante_id)
values ('Cachorro quente', 'Cachorro preensado com 2 salsishas', 15.50, 'LOCAL', (select id from restaurante limit 1));

insert into item (nome, descricao, preco, tipo_venda, restaurante_id)
values ('Macarronese de frango', 'Melhor macarronese da região', 103.52, 'DELIVERY', (select id from restaurante limit 1));