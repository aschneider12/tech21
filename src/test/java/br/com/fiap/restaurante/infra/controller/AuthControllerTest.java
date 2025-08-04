package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.usecases.itemcardapio.*;
import br.com.fiap.restaurante.application.usecases.usuario.FactoryUsuarioUseCase;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseGerarTokenUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseValidarLogin;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseValidarToken;
import br.com.fiap.restaurante.infra.controller.handlers.ExceptionController;
import br.com.fiap.restaurante.infra.dtos.LoginRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FactoryUsuarioUseCase factoryUseCase;

    @Mock
    private UseCaseValidarToken useCaseValidarToken;

    @Mock
    private UseCaseValidarLogin useCaseValidarLogin;

    @Mock
    private UseCaseGerarTokenUsuario useCaseGerarTokenUsuario;


    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        AuthController authController = new AuthController(factoryUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new ExceptionController())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermtirAutenticarUsuarioComCredenciaisValidas() throws Exception {
        var login = "usuario@teste.com";
        var senha = "123456";
        var token = "token123456789";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(login);
        loginRequest.setSenha(senha);

        when(factoryUseCase.validarLogin()).thenReturn(useCaseValidarLogin);
        when(useCaseValidarLogin.run(login, senha)).thenReturn(true);

        when(factoryUseCase.gerarToken()).thenReturn(useCaseGerarTokenUsuario);
        when(useCaseGerarTokenUsuario.run(login)).thenReturn(token);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Usuário: " + login)))
                .andExpect(content().string(containsString("Token: " + token)));

        verify(factoryUseCase, times(1)).validarLogin();
        verify(factoryUseCase, times(1)).gerarToken();
        verify(useCaseValidarLogin, times(1)).run(login, senha);
        verify(useCaseGerarTokenUsuario, times(1)).run(login);
    }

    @Test
    void naoDevePermtirAutenticarUsuarioComCredenciaisInvalidas() throws Exception {
        var login = "usuario@teste.com";
        var senha = "senhaErrada";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(login);
        loginRequest.setSenha(senha);

        when(factoryUseCase.validarLogin()).thenReturn(useCaseValidarLogin);
        when(useCaseValidarLogin.run(login, senha)).thenReturn(false);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginRequest)))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(containsString("Credenciais inválidas")));

        verify(factoryUseCase, times(1)).validarLogin();
        verify(useCaseValidarLogin, times(1)).run(login, senha);
        verify(factoryUseCase, never()).gerarToken();
    }

    @Test
    void deveValidarTokenComSucesso() throws Exception {
        String token = "token123456789";

        when(factoryUseCase.validarToken()).thenReturn(useCaseValidarToken);
        when(useCaseValidarToken.run(token)).thenReturn(true);

        mockMvc.perform(post("/auth/validar-token")
                        .header("authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Token é válido."));

        verify(factoryUseCase, times(1)).validarToken();
        verify(useCaseValidarToken, times(1)).run(token);
    }

    @Test
    void naoDeveValidarTokenInvalido() throws Exception {
        String token = "token123456789";

        when(factoryUseCase.validarToken()).thenReturn(useCaseValidarToken);
        when(useCaseValidarToken.run(token)).thenReturn(false);

        mockMvc.perform(post("/auth/validar-token")
                        .header("authorization", token))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(content().string("Token enviado é inválido"));

        verify(factoryUseCase, times(1)).validarToken();
        verify(useCaseValidarToken, times(1)).run(token);
    }

    private String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
