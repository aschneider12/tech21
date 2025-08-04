# tech21 Grupo 83 - Tech Challenge - Share Food

## O Projeto

O sistema proposto unifica clientes e donos de restaurantes em uma estrutura multiusuário, mantendo isolamento lógico e controle individualizado dos dados. O sistema foi construído com foco em escalabilidade e fácil manutenção, aplicando os princípios da Clean Architecture.

## A Arquitetura

A estrutura do sistema é baseada na separação em camadas conforme os princípios da Clean Architecture:
- `domain`: modelos e interfaces centrais da aplicação, totalmente isolados de tecnologias externas.
- `application`: casos de uso e lógica de negócio, com injeção de dependência via interfaces (Gateways).
- `infra`: implementações técnicas como repositórios JPA, controllers REST, configuração de segurança, Swagger e persistência.

## Tecnologias utilizadas

- Java 21  
- Spring Boot 3.4.4  
- Spring Security (JWT)  
- PostgreSQL  
- Flyway  
- Swagger (SpringDoc)  
- Docker & Docker Compose
- MapStruct
- JUnit 5
- Mockito
- MockMvc

## Testes Automatizados
O projeto possui cobertura automatizada para os principais fluxos com uso de:

- JUnit 5 para estruturação dos testes
- Mockito para mocks de dependências
- Spring MockMvc para testes de controladores
- Helper.java para dados de teste reutilizáveis
  

## Como executar o projeto

Certifique-se de ter **Docker** e **Docker Compose** instalados.

Execute o comando abaixo na raiz do projeto:

```bash
docker compose -f ./docker-compose.yml up -d
```

Para acompanhar os logs do backend:

```bash
docker logs -f spring-restaurante
```

Para acessar o banco de dados PostgreSQL via terminal:

```bash
docker exec -it postgres-restaurante psql -U postgres -d restaurante
```

---

## Autenticação JWT

### 1. Cadastrar um usuário

```bash
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

```bash
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

```bash
curl -X GET http://localhost:8080/usuarioEntity \
  -H "Authorization: Bearer SEU_TOKEN_JWT"
```

---

## Trocar senha autenticado

```bash
curl -X PATCH http://localhost:8080/usuarioEntity/mudar-senha/ID_USUARIO \
  -H "Authorization: Bearer SEU_TOKEN_JWT" \
  -H "Content-Type: application/json" \
  -d '{
    "senhaAtual": "SenhaForte123",
    "novaSenha": "NovaSenha456"
  }'
```

Resposta esperada:

```json
{
  "message": "Senha atualizada com sucesso."
}
```

---

## Executar testes automatizados

Se estiver usando o Maven localmente:

```bash
./mvnw test
```

---

## Documentação da API (Swagger)

Após subir o backend, acesse:

- [Swagger UI](http://localhost:8080/swagger-ui/index.html)

---

## Configuração de variáveis de ambiente

Arquivo `application.properties`:

```properties
jwt.secret=${TOKEN_JWT_SECRET}
jwt.expiration=3600000
```

> O segredo JWT será carregado exclusivamente por variáveis de ambiente em ambientes produtivos, conforme boas práticas de segurança.

---

## Autores

- [Alessandro Schneider](https://github.com/aschneider12)
- [Raquel Morabito](https://github.com/raquelmorabito)
- [Eduardo Serafim](https://github.com/EduardoSerafim)
- [Natan Campos](https://github.com/Tune-SKT)
- [Henrique Danzo](https://github.com/danzobiss)

Desenvolvido como parte do projeto Tech Challenge - FIAP
