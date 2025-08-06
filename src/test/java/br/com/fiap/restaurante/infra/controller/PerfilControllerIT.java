package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
import br.com.fiap.restaurante.infra.dtos.PerfilRequestDTO;
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

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/db_restaurante_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = {"/db_restaurante_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class PerfilControllerIT {

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
    void devePermitirListarTodosOsPerfisDoUsuario() throws Exception {
        Long idUsuario = 1111L;
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
                .when().get("/usuario/{id}/perfil", idUsuario)
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .body("size()", greaterThan(0));
    }


    @Test
    void devePermitirAdicionarPerfisParaUsuario() throws Exception {
        Long idUsuario = 1111L;

        PerfilRequestDTO perfisDto = new PerfilRequestDTO(List.of(TipoUsuario.CLIENTE));



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
                .body(perfisDto)
                .when().post("/usuario/{id}/perfil", idUsuario)
                .then().statusCode(HttpStatus.CREATED.value())
                .log().all();

    }


    @Test
    void devePermitirRemoverPerfisParaUsuario() throws Exception {
        Long idUsuario = 1111L;

        PerfilRequestDTO perfisDto = new PerfilRequestDTO(List.of(TipoUsuario.DONO));



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
                .body(perfisDto)
                .when().delete("/usuario/{id}/perfil", idUsuario)
                .then().statusCode(HttpStatus.NO_CONTENT.value())
                .log().all();

    }


}
