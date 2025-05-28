## Como subir o projeto

docker-compose up --build -d
docker logs -f spring-restaurante
---
docker exec -it postgres-restaurante psql -U postgres -d restaurante

post test


Certifique-se de ter o **Docker** e o **Docker Compose** instalados.  
Execute o seguinte comando no terminal dentro da pasta do projeto:


Para verificar se o backend subiu corretamente:


## Como testar a autenticação JWT

### 1. Cadastrar um usuário
curl -X POST http://localhost:8080/usuario \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Fazendo Teste",
    "email": "fazendoteste@fazendoteste.com",
    "login": "fazendoteste",
    "senha": "654321",
    "tipoUsuario": "DONO"
  }'
```

### 2. Realizar login para gerar o token JWT
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "login": "fazendoteste",
    "senha": "654321",
    "tipoUsuario": "DONO"
  }'
```

O retorno será um token. Copie apenas o valor do token JWT (sem aspas ou prefixos).

### 3. Acessar endpoints protegidos com o token
curl -X GET http://localhost:8080/usuario \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

---

## Como trocar a senha

curl -X PUT "http://localhost:8080/auth/senha?login=fazendoteste&novaSenha=123456"
```

---

## Swagger (Documentação da API)

Disponível após subir o backend:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Variáveis de ambiente

O segredo usado para assinar os JWTs está atualmente sendo lido do arquivo `application.properties`, mas no futuro sera passada via variável de ambiente no futuro:

```properties
jwt.secret=${TOKEN_JWT_SECRET}
jwt.expiration=3600000
```

Para configurar corretamente, defina a variável no seu ambiente local ou via Docker Compose (futuramente o projeto será ajustado para isso).

---

## Testes

Os testes automatizados estão localizados em `src/test/java/...`.  
Para executá-los (caso esteja rodando localmente com Maven):

./mvnw test
```

---

## Tecnologias principais

- Spring Boot
- Spring Security (JWT)
- PostgreSQL
- Docker
- Swagger (SpringDoc)
