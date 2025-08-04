package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.usecases.restaurante.FactoryRestauranteUseCase;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseAdicionarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseBuscarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseRemoverPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseAlterarSenhaUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseGerarTokenUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseValidarLogin;
import br.com.fiap.restaurante.application.usecases.usuario.senha.UseCaseValidarToken;
import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.infra.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FactoryUsuarioUseCaseTest {

    @Mock
    private IUsuarioGateway gateway;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;


    private FactoryUsuarioUseCase factory;

    @BeforeEach
    void setUp() {
        factory = new FactoryUsuarioUseCase(gateway, passwordEncoder, jwtService);
    }

    @Test
    void devePermtirCriarUseCaseAtualizarUsuario() {
        var useCase = factory.atualizarUsuario();
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseAtualizarUsuario.class);
    }

    @Test
    void devePermtirCriarUseCaseBuscarTodosUsuarios() {
        var useCase = factory.buscarTodosUsuarios();
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseBuscarTodosUsuarios.class);
    }

    @Test
    void devePermtirCriarUseCaseBuscarUsuarioPorID() {
        var useCase = factory.buscarUsuarioPorID();
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseBuscarUsuarioPorID.class);
    }

    @Test
    void devePermtirCriarUseCaseCadastrarUsuario() {
        var useCase = factory.cadastrarUsuario();
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseCadastrarUsuario.class);
    }

    @Test
    void devePermtirCriarUseCaseDeletarUsuario() {
        var useCase = factory.deletarUsuario();
        assertNotNull(useCase);
        assertInstanceOf(UseCaseDeletarUsuario.class, useCase);
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseDeletarUsuario.class);

    }

    @Test
    void devePermtirCriarUseCaseBuscarPerfisUsuario() {
        var useCase = factory.buscarPerfisUsuario();
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseBuscarPerfisUsuario.class);

    }

    @Test
    void devePermtirCriarUseCaseAdicionarPerfisUsuario() {
        var useCase = factory.adicionarPerfisUsuario();
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseAdicionarPerfisUsuario.class);
    }

    @Test
    void devePermtirCriarUseCaseRemoverPerfisUsuario() {
        var useCase = factory.removerPerfisUsuario();
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseRemoverPerfisUsuario.class);
    }

    @Test
    void devePermtirCriarUseCaseAlterarSenhaUsuario() {
        var useCase = factory.alterarSenhaUsuario();
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseAlterarSenhaUsuario.class);
    }

    @Test
    void devePermtirCriarUseCaseValidarToken() {
        var useCase = factory.validarToken();
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseValidarToken.class);
    }

    @Test
    void devePermtirCriarUseCaseValidarLogin() {
        var useCase = factory.validarLogin();
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseValidarLogin.class);
    }

    @Test
    void devePermtirCriarUseCaseGerarToken() {
        var useCase = factory.gerarToken();
        assertThat(useCase).
                isNotNull()
                .isInstanceOf(UseCaseGerarTokenUsuario.class);
    }
}
