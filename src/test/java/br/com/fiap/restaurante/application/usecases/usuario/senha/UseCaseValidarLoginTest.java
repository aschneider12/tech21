package br.com.fiap.restaurante.application.usecases.usuario.senha;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UseCaseValidarLoginTest {

    @Mock
    private IUsuarioGateway gateway;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UseCaseValidarLogin useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseValidarLogin.create(gateway, passwordEncoder);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void deveRetornarTrueParaLoginSenhaCorretos() {
        String login = "usuario";
        String senhaDigitada = "Senha@123";
        String senhaArmazenada = "Senha@123"; // sem hash para teste simples

        Usuario usuario = Helper.gerarUsuario();
        usuario.setLogin(login);
        usuario.setSenha(senhaArmazenada);

        when(gateway.buscarUsuarioPorLogin(login)).thenReturn(usuario);
        when(passwordEncoder.matches(senhaDigitada, senhaArmazenada)).thenReturn(true);

        boolean resultado = useCase.run(login, senhaDigitada);

        assertThat(resultado).isTrue();
        verify(gateway, times(1)).buscarUsuarioPorLogin(login);
        verify(passwordEncoder, times(1)).matches(senhaDigitada, senhaArmazenada);
    }

    @Test
    void deveRetornarFalseParaSenhaIncorreta() {
        String login = "usuario";
        String senhaDigitada = "senhaErrada";
        String senhaArmazenada = "Senha@123";

        Usuario usuario = Helper.gerarUsuario();
        usuario.setLogin(login);
        usuario.setSenha(senhaArmazenada);

        when(gateway.buscarUsuarioPorLogin(login)).thenReturn(usuario);
        when(passwordEncoder.matches(senhaDigitada, senhaArmazenada)).thenReturn(false);

        boolean resultado = useCase.run(login, senhaDigitada);

        assertThat(resultado).isFalse();
        verify(gateway, times(1)).buscarUsuarioPorLogin(login);
        verify(passwordEncoder, times(1)).matches(senhaDigitada, senhaArmazenada);
    }

    @Test
    void deveRetornarFalseSeLoginNaoExistir() {
        String login = "naoExiste";
        String senha = "qualquerSenha";

        when(gateway.buscarUsuarioPorLogin(login))
                .thenThrow(new EntidadeNaoEncontradaException("Usuário", login));

        boolean resultado = useCase.run(login, senha);

        assertThat(resultado).isFalse();
        verify(gateway, times(1)).buscarUsuarioPorLogin(login);
        verify(passwordEncoder, never()).matches(any(), any());
    }

    @Test
    void deveRetornarFalseSeSenhaDoBancoForNula() {
        String login = "usuario";
        String senhaDigitada = "qualquer";

        Usuario usuario = Helper.gerarUsuario();
        usuario.setLogin(login);
        usuario.setSenha(null);

        when(gateway.buscarUsuarioPorLogin(login)).thenReturn(usuario);
        when(passwordEncoder.matches(senhaDigitada, null)).thenReturn(false);

        boolean resultado = useCase.run(login, senhaDigitada);

        assertThat(resultado).isFalse();
        verify(passwordEncoder).matches(senhaDigitada, null);
    }

    @Test
    void deveRetornarFalseSeSenhaDigitadaForNula() {
        String login = "usuario";

        Usuario usuario = Helper.gerarUsuario();
        usuario.setLogin(login);
        usuario.setSenha("Senha@123");

        when(gateway.buscarUsuarioPorLogin(login)).thenReturn(usuario);
        when(passwordEncoder.matches(null, "Senha@123")).thenReturn(false);

        boolean resultado = useCase.run(login, null);

        assertThat(resultado).isFalse();
    }

    @Test
    void deveRetornarFalseSeLoginForNulo() {
        String senha = "qualquer";

        when(gateway.buscarUsuarioPorLogin(null))
                .thenThrow(new EntidadeNaoEncontradaException("Usuário", null));

        boolean resultado = useCase.run(null, senha);

        assertThat(resultado).isFalse();
        verify(gateway).buscarUsuarioPorLogin(null);
    }
}