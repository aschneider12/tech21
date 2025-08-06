package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.dtos.MudarSenhaDTO;
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
public class UsuarioControllerIT {

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
    void devePermitirCadastrarUsuario() throws Exception {
        var usuario = Helper.gerarUsuario();
        usuario.setNome("Novo usuário teste");
        usuario.setEmail("novousuario@teste.com");
        usuario.getEndereco().setId(null);
        var usuarioDto = Helper.gerarUsuarioInsertDTO(usuario);


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
                .body(usuarioDto)
                .when().post("/usuario")
                .then().statusCode(HttpStatus.CREATED.value())
                .log().all()
                .body("$", hasKey("id"))
                .body("$", hasKey("nome"))
                .body("$", hasKey("email"))
                .body("$", hasKey("login"))
                .body("nome", equalTo(usuarioDto.nome()))
                .body("email", equalTo(usuarioDto.email()))
                .body("login", equalTo(usuarioDto.login()));

    }


    @Test
    void devePermitirAtualizarUsuario() throws Exception {

        Long idUsuario = 9999L;
        var usuario = Helper.gerarUsuario();

        usuario.setNome("Novo usuario para atualizar teste");
        var usuarioDto = Helper.gerarUsuarioUpdateDTO(usuario);



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
                .body(usuarioDto)
                .when().put("/usuario/{id}", idUsuario)
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .body("$", hasKey("id"))
                .body("$", hasKey("nome"))
                .body("$", hasKey("email"))
                .body("$", hasKey("login"))
                .body("nome", equalTo(usuarioDto.nome()))
                .body("email", equalTo(usuarioDto.email()))
                .body("login", equalTo(usuarioDto.login()));
    }


    @Test
    void devePermitirDeletarUsuario() throws Exception {
        Long idUsuario = 9999L;

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
                .when().delete("/usuario/{id}", idUsuario)
                .then().statusCode(HttpStatus.NO_CONTENT.value())
                .log().all();

    }


    @Test
    void devePermitirBuscarTodosOsUsuarios() throws Exception {

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
                .when().get("/usuario")
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .body("size()", greaterThan(0));
    }

    @Test
    void devePermitirBuscarUsuarioPorId() throws Exception {
        Long idUsuario = 9999L;

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
                .when().get("/usuario/{id}",  idUsuario)
                .then().statusCode(HttpStatus.OK.value())
                .log().all()
                .body("size()", greaterThan(0))
                .body("$", hasKey("id"))
                .body("$", hasKey("nome"))
                .body("$", hasKey("email"))
                .body("$", hasKey("login"))
                .body("id", equalTo(9999))
                .body("nome", equalTo("João Teste"))
                .body("email", equalTo("joao@teste.com"))
                .body("login", equalTo("joaoteste"));
    }


    @Test
    void devePermitirAlterarSenhaDoUsuario() throws Exception {
        Long idUsuario = 9999L;
        String senhaNova = "NovaSenha1234*";
        String senhaAntiga = "Teste123*";
        var mudarSenhaDTO = new MudarSenhaDTO(senhaAntiga, senhaNova);

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
                .body(mudarSenhaDTO)
                .when().patch("/usuario/{id}/mudar-senha",  idUsuario)
                .then().statusCode(HttpStatus.NO_CONTENT.value())
                .log().all();
    }



}
