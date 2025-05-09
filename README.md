# tech21 Grupo 21 - Tech Challenge 

https://encurtador.com.br/5XOW7

## Como executar o projeto:

**Clone o repositório**

> `git clone https://github.com/<seu-usuario>/tech21.git`
> `cd tech 21`

**Suba os containers com Docker Compose** 

> `docker-compose up --build -d`

**Verifique se os containers estão rodando** 
>`docker ps`

**Verifique os logs da aplicação**
>`docker logs -f spring-restaurante`

**Acesse a aplicação **
Acesse 
>`http://localhost:8080/usuario`
endpoints ainda serão adicionados, enquanto isso aparece: Whitelabel Error Page

Como parar o projeto 
>`docker-compose down` 

Ou para remover volumes (dados do banco):
>`docker-compose down -v`

O que ainda falta implementar (próximos passos) 
...
Endpoint para atualizar dados do usuário 
Endpoint para troca de senha 
Endpoint para validação de login
...

Para rodar o pgAdmin e inspecionar o banco rodar separado
    * caso não tenha o pgAdmin instalado localmente
> docker-compose -f .\compose-pgadmin.yml up -d

Acessar em: http://localhost:5050/login?next=/
Para conectar usar: como server <postgres> não <localhost>