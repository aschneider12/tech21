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
        'teste',
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