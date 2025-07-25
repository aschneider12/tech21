# tech21 Grupo 83 - Tech Challenge 


## Como executar o projeto:


Certifique-se de ter **Docker** e **Docker Compose** instalados.

Execute o comando abaixo na raiz do projeto:

```
docker compose -f .\docker-compose.yml up -d
```

Para acompanhar os logs do backend:

```
docker logs -f spring-restaurante
```

Para acessar o banco de dados PostgreSQL via terminal:

```
docker exec -it postgres-restaurante psql -U postgres -d restaurante
```

---

## Autenticação JWT

### 1. Cadastrar um usuário

```
curl -X POST http://localhost:8080/usuarioEntity \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Fazendo Teste",
    "email": "fazendoteste@fazendoteste.com",
    "login": "fazendoteste",
    "senha": "SenhaForte123",
    "tipoUsuario": "DONO"
  }'
```

> Senha precisa ter no mínimo 8 caracteres e conter números.

### 2. Realizar login e obter o token JWT

```
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "login": "fazendoteste",
    "senha": "SenhaForte123",
    "tipoUsuario": "DONO"
  }'
```

> A resposta conterá o token JWT. Copie somente o valor do token para usar nas próximas requisições.

### 3. Acessar endpoint protegido com token

```
curl -X GET http://localhost:8080/usuarioEntity \
  -H "Authorization: Bearer SEU_TOKEN_JWT"
```

---

## Trocar senha autenticado

```
curl -X PATCH http://localhost:8080/usuarioEntity/mudar-senha/ID_USUARIO \
  -H "Authorization: Bearer SEU_TOKEN_JWT" \
  -H "Content-Type: application/json" \
  -d '{
    "senhaAtual": "SenhaForte123",
    "novaSenha": "NovaSenha456"
  }'
```

Resposta esperada:

```
{
  "message": "Senha atualizada com sucesso."
}
```

---

## Executar testes automatizados

Se estiver usando o Maven localmente:

```
./mvnw test
```

---

## Documentação da API (Swagger)

Após subir o backend, acesse:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Configuração de variáveis de ambiente

Arquivo `application.properties`:

```
jwt.secret=${TOKEN_JWT_SECRET}
jwt.expiration=3600000
```

> Em breve o segredo será carregado exclusivamente via variáveis de ambiente para ambientes produtivos.

---

## Tecnologias utilizadas

- Java 21  
- Spring Boot 3.4.4  
- Spring Security (JWT)  
- PostgreSQL  
- Flyway  
- Swagger (SpringDoc)  
- Docker & Docker Compose  

---

## Autores

- [Alessandro Schneider](https://github.com/aschneider12)
- [Raquel Morabito](https://github.com/raquelmorabito)
- [Eduardo Serafim](https://github.com/EduardoSerafim)
- [Natan Campos](https://github.com/Tune-SKT)
- [Henrique Danzo](https://github.com/danzobiss)

Desenvolvido como parte do projeto Tech Challenge - FIAP
