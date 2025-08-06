package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.helper.Helper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/db_restaurante_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = {"/db_restaurante_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class ItemCardapioControllerIT {

    @LocalServerPort
    private int port;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermtirBuscarItemCardapioPorId() throws Exception {
        Long idItem = 9999L;
        Long idRestaurante = 9999L;

        String retornoToken =
                given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(Map.of(
                                "login", "adminteste",
                                "senha", "teste"
                        ))
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString();


        Pattern jwtPattern = Pattern.compile("[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+");
        Matcher matcher = jwtPattern.matcher(retornoToken);
        if (!matcher.find()) {
            throw new AssertionError("JWT não encontrado na resposta de login: " + retornoToken);
        }
        String token = matcher.group();



        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + token)

                .when().get("/restaurante/{restauranteId}/cardapio/{id}", idRestaurante, idItem)
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .body("$", hasKey("id"))
                .body("$", hasKey("nome"))
                .body("$", hasKey("descricao"))
                .body("nome", equalTo("item teste"))
                .body("descricao", equalTo("descricao teste"));


    }


    @Test
    void devePermtirBuscarTodosItemCardapio() throws Exception {

        Long idRestaurante = 9999L;

        String retornoToken =
                given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(Map.of(
                                "login", "adminteste",
                                "senha", "teste"
                        ))
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString();


        Pattern jwtPattern = Pattern.compile("[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+");
        Matcher matcher = jwtPattern.matcher(retornoToken);
        if (!matcher.find()) {
            throw new AssertionError("JWT não encontrado na resposta de login: " + retornoToken);
        }
        String token = matcher.group();



        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + token)
                .when().get("/restaurante/{restauranteId}/cardapio", idRestaurante)
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .body("size()", greaterThan(0));
    }



    @Test
    void devePermtirCadastrarItemCardapio() throws Exception {
        Long idRestaurante = 9999L;
        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.setNome("Novo item cardápio");
        itemCardapio.getRestaurante().setId(idRestaurante);
        var itemDTO = Helper.gerarItemRequestDTO(itemCardapio);
        itemDTO.setRestauranteId(idRestaurante);

        String retornoToken =
                given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(Map.of(
                                "login", "adminteste",
                                "senha", "teste"
                        ))
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString();


        Pattern jwtPattern = Pattern.compile("[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+");
        Matcher matcher = jwtPattern.matcher(retornoToken);
        if (!matcher.find()) {
            throw new AssertionError("JWT não encontrado na resposta de login: " + retornoToken);
        }
        String token = matcher.group();



        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + token)
                .body(itemDTO)
                .when().post("/restaurante/{restauranteId}/cardapio", idRestaurante)
                .then().statusCode(HttpStatus.CREATED.value())
                .log().all()
                .body("$", hasKey("id"))
                .body("$", hasKey("nome"))
                .body("$", hasKey("descricao"))
                .body("nome", equalTo(itemCardapio.getNome()))
                .body("descricao", equalTo(itemCardapio.getDescricao()));


    }


    @Test
    void devePermtirAtualizarItemCardapio() throws Exception {
        Long idItem = 9999L;
        Long idRestaurante = 9999L;
        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.setNome("item cardápio Atualizado");
        itemCardapio.getRestaurante().setId(idRestaurante);
        var itemDTO = Helper.gerarItemRequestDTO(itemCardapio);
        itemDTO.setRestauranteId(idRestaurante);

        String retornoToken =
                given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(Map.of(
                                "login", "adminteste",
                                "senha", "teste"
                        ))
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString();


        Pattern jwtPattern = Pattern.compile("[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+");
        Matcher matcher = jwtPattern.matcher(retornoToken);
        if (!matcher.find()) {
            throw new AssertionError("JWT não encontrado na resposta de login: " + retornoToken);
        }
        String token = matcher.group();



        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + token)
                .body(itemDTO)
                .when().put("/restaurante/{restauranteId}/cardapio/{id}", idRestaurante, idItem)
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .body("$", hasKey("id"))
                .body("$", hasKey("nome"))
                .body("$", hasKey("descricao"))
                .body("nome", equalTo(itemCardapio.getNome()))
                .body("descricao", equalTo(itemCardapio.getDescricao()));


    }


    @Test
    void devePermtirDeletarItemCardapio() throws Exception {
        Long idItem = 9999L;
        Long idRestaurante = 9999L;


        String retornoToken =
                given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(Map.of(
                                "login", "adminteste",
                                "senha", "teste"
                        ))
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString();


        Pattern jwtPattern = Pattern.compile("[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+");
        Matcher matcher = jwtPattern.matcher(retornoToken);
        if (!matcher.find()) {
            throw new AssertionError("JWT não encontrado na resposta de login: " + retornoToken);
        }
        String token = matcher.group();



        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + token)
                .when().delete("/restaurante/{restauranteId}/cardapio/{id}", idRestaurante, idItem)
                .then().statusCode(HttpStatus.NO_CONTENT.value())
                .log().all();



    }

}

