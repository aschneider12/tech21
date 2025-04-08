# Para subir o container do projeto use o comando abaixo
# $ docker build -t postech/spring-restaurante:<tag_de_versao> .

#Neste momento usamos o maven
FROM maven:3.9.9-amazoncorretto-21-al2023 AS builder
#Define a pasta de trabalho como /app
WORKDIR /app
#Copia os arquivos para a pasta de trabalho .
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Fase de rodar o projeto
FROM openjdk:21-slim

WORKDIR /app

COPY --from=builder /app/target/*.jar restaurante.jar

# demonstra que o container irá expor a porta 8080, alinhado com a configuração do projeto Spring
EXPOSE 8080

ENTRYPOINT ["java","-jar","restaurante.jar"]
