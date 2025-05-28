## Como subir o projeto

Certifique-se de ter o **Docker** e o **Docker Compose** instalados.  
No diretório raiz do projeto, execute:

```bash
docker-compose up --build -d
```

Para acompanhar os logs da aplicação backend:

```bash
docker logs -f spring-restaurante
```

Para acessar o banco de dados:

```bash
docker exec -it postgres-restaurante psql -U postgres -d restaurante
```

---

## Como testar a autenticação JWT

### 1. Cadastrar um usuário

```bash
curl -X POST http://localhost:8080/usuario \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Fazendo Teste",
    "email": "fazendoteste@fazendoteste.com",
    "login": "fazendoteste",
    "senha": "SenhaForte123",
    "tipoUsuario": "DONO"
  }'
```

### 2. Realizar login para gerar o token JWT

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "login": "fazendoteste",
    "senha": "SenhaForte123",
    "tipoUsuario": "DONO"
  }'
```

O retorno incluirá o token JWT. Copie **somente** o valor do token (sem aspas ou prefixos).

### 3. Acessar endpoints protegidos

```bash
curl -X GET http://localhost:8080/usuario \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

---

## Como trocar a senha

```bash
curl -X PATCH http://localhost:8080/usuario/mudar-senha/ID_DO_USUARIO \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -H "Content-Type: application/json" \
  -d '{
    "senhaAtual": "SenhaForte123",
    "novaSenha": "NovaSenha456"
  }'
```

---

## Swagger (Documentação da API)

Após a aplicação subir, a documentação estará disponível em:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Variáveis de ambiente

O segredo usado para assinar os JWTs está atualmente em `application.properties`.  
Em breve, será lido por variável de ambiente:

```properties
jwt.secret=${TOKEN_JWT_SECRET}
jwt.expiration=3600000
```

Para ambientes futuros, defina `TOKEN_JWT_SECRET` no sistema operacional ou no `docker-compose.yml`.

---

## Testes automatizados

Os testes estão localizados em `src/test/java/...`.

Para executá-los localmente:

```bash
./mvnw test
```

---

## Tecnologias utilizadas

- Spring Boot 3.4.4  
- Spring Security com JWT  
- PostgreSQL  
- Docker e Docker Compose  
- Swagger (via SpringDoc)  
- Flyway para versionamento de banco de dados
