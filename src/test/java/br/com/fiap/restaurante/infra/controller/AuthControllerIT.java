package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.infra.dtos.LoginRequest;
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
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/db_restaurante_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = {"/db_restaurante_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AuthControllerIT {

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
    void devePermtirAutenticarUsuarioComCredenciaisValidas(){
        var login = "adminteste";
        var senha = "teste";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(login);
        loginRequest.setSenha(senha);

        String response =
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(loginRequest)
                    .when()
                    .post("/auth/login")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .log().all()
                    .extract().asString();


        assertThat(response)
                .contains("Token");


        Pattern jwtPattern = Pattern.compile("[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+");
        Matcher matcher = jwtPattern.matcher(response);
        assertThat(matcher.find())
                .isTrue();
        String token = matcher.group();


        assertThat(token)
                .isNotBlank()
                .hasSizeGreaterThan(20);

    }


    @Test
    void deveValidarToken() {
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


        var response =
                given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .post("/auth/validar-token")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .asString();

        assertThat(response)
                .isEqualTo("Token é válido.");
    }
}
