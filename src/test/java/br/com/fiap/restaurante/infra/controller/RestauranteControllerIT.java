package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.helper.Helper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/db_restaurante_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = {"/db_restaurante_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RestauranteControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @Test
    void devePermitirCadastrarRestaurante() throws Exception {
        var restaurante = Helper.gerarRestaurante();
        restaurante.setNome("Novo Restaurante teste");
        restaurante.setTipoCozinha("ORIENTAL");
        restaurante.getDono().setId(9999L);
        restaurante.getEndereco().setId(null);
        var restauranteDto = Helper.gerarRestauranteDTO(restaurante);


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
                .body(restauranteDto)
                .when().post("/restaurante")
                .then().statusCode(HttpStatus.CREATED.value())
                .log().all()
                .body("$", hasKey("id"))
                .body("$", hasKey("nome"))
                .body("$", hasKey("tipoCozinha"))
                .body("$", hasKey("horarioFuncionamento"))
                .body("$", hasKey("dono"))
                .body("nome", equalTo(restauranteDto.nome()))
                .body("tipoCozinha", equalTo(restauranteDto.tipoCozinha()))
                .body("horarioFuncionamento", equalTo(restauranteDto.horarioFuncionamento()))
                .body("dono", equalTo(restauranteDto.dono().intValue()));
    }


    @Test
    void devePermitirAtualizarRestaurante() throws Exception {
        Long idRestaurante = 9999L;
        Long idDono = 9999L;
        var restaurante = Helper.gerarRestaurante();
        restaurante.getDono().setId(idDono);
        restaurante.setNome("Novo Restaurante para atualizar teste");
        var restauranteDto = Helper.gerarRestauranteDTO(restaurante);



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
                .body(restauranteDto)
                .when().put("/restaurante/{id}", idRestaurante)
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .body("$", hasKey("id"))
                .body("$", hasKey("nome"))
                .body("$", hasKey("tipoCozinha"))
                .body("$", hasKey("horarioFuncionamento"))
                .body("$", hasKey("dono"))
                .body("nome", equalTo(restauranteDto.nome()))
                .body("tipoCozinha", equalTo(restauranteDto.tipoCozinha()))
                .body("horarioFuncionamento", equalTo(restauranteDto.horarioFuncionamento()))
                .body("dono", equalTo(restauranteDto.dono().intValue()));
    }

    @Test
    void devePermitirDeletarRestaurante() throws Exception {
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
                .when().delete("/restaurante/{id}", idRestaurante)
                .then().statusCode(HttpStatus.NO_CONTENT.value())
                .log().all();

    }


    @Test
    void devePermitirBuscarRestaurantePorId() throws Exception {
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
                .when().get("/restaurante/{id}", idRestaurante)
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .body("size()", greaterThan(0))
                .body("$", hasKey("nome"))
                .body("$", hasKey("tipoCozinha"))
                .body("$", hasKey("horarioFuncionamento"))
                .body("$", hasKey("dono"))
                .body("nome", equalTo("Restaurante teste"))
                .body("tipoCozinha", equalTo("COZINHA DE TESTE"))
                .body("horarioFuncionamento", equalTo("Atendimento das 11h as 22h"));
    }


    @Test
    void devePermitirBuscarTodosOsRestaurantes() throws Exception {

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
                .when().get("/restaurante")
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .body("size()", greaterThan(0));
    }



}
