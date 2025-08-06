
INSERT INTO endereco(id, rua, numero, cidade, estado, cep)
VALUES (9999,
        'Endereço Restaurante Teste',
        '1234',
        'São Paulo',
        'SP',
        '12.345-789');

INSERT INTO endereco(id, rua, numero, cidade, estado, cep)
VALUES (10000,
        'Endereço Dono Teste',
        '1234',
        'São Paulo',
        'SP',
        '12.345-789');

INSERT INTO usuario(id, nome, email, login, senha, data_ultima_alteracao, endereco_id)
VALUES( 9999,
        'João Teste',
        'joao@teste.com',
        'joaoteste',
        '$2a$10$oMsATmzOka2x64jsj4Y0B.xcd4ouItGufSzQKu6us4mafN7EcelJq', --Teste123*
        NOW(),
        10000);

INSERT INTO restaurante(id, nome, endereco_id, tipo_cozinha, horario_funcionamento, usuario_id)
VALUES(9999,
       'Restaurante teste',
       9999,
       'COZINHA DE TESTE',
       'Atendimento das 11h as 22h',
       9999);

INSERT INTO item(id, nome, descricao, preco, tipo_venda, restaurante_id, path_foto)
VALUES(9999,
    'item teste',
    'descricao teste',
    10.0,
    'LOCAL',
    9999,
    null);

INSERT INTO usuario(id, nome, email, login, senha, data_ultima_alteracao, endereco_id)
VALUES( 10000,
        'Usuário ADMIN para testes',
        'testes@teste.com',
        'adminteste',
        '$2a$10$eK9D.NrnjSDoMF5poXxOpOYg4cdafq3hrtA4yAdkQDII6SuQaaFry', -- a senha criptografada é 'teste'
        NOW(),
        10000);

INSERT INTO usuario(id, nome, email, login, senha, data_ultima_alteracao, endereco_id) -- Usuario para testes de perfil
VALUES( 1111,
        'João Teste perfil',
        'joaoperfil@teste.com',
        'joaotesteperfil',
        '$2a$10$oMsATmzOka2x64jsj4Y0B.xcd4ouItGufSzQKu6us4mafN7EcelJq', --Teste123*
        NOW(),
        10000);

INSERT INTO usuario_perfil(usuario_id, tipo_usuario)
VALUES( 1111,
        'DONO'
     );