package br.com.fiap.restaurante.application.usecases.usuario.senha;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class UseCaseAlterarSenhaUsuarioTest {

    @Mock
    private IUsuarioGateway gateway;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UseCaseAlterarSenhaUsuario useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseAlterarSenhaUsuario.create(gateway, passwordEncoder);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void deveAlterarSenhaComSucesso() {
        Long id = 1L;
        String senhaAntiga = "Senha@123";
        String senhaNova = "NovaSenha@456";
        String hashNova = "hashed";

        Usuario usuario = Helper.gerarUsuario();
        usuario.setId(id);
        usuario.setSenha(senhaAntiga);

        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);
        when(passwordEncoder.matches(senhaAntiga, senhaAntiga)).thenReturn(true);
        when(passwordEncoder.encode(senhaNova)).thenReturn(hashNova);

        useCase.run(id, senhaAntiga, senhaNova);

        verify(gateway, times(1)).atualizarNovaSenhaUsuario(id, hashNova);
    }

    @Test
    void deveFalharSeSenhaAntigaEstiverIncorreta() {
        Long id = 1L;
        Usuario usuario = Helper.gerarUsuario();
        usuario.setId(id);
        usuario.setSenha("SenhaCorreta");

        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);
        when(passwordEncoder.matches("SenhaErrada", "SenhaCorreta")).thenReturn(false);

        assertThatThrownBy(() -> useCase.run(id, "SenhaErrada", "NovaSenha@456"))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Senha antiga não confere.");

        verify(gateway, never()).atualizarNovaSenhaUsuario(any(), any());
    }

    @Test
    void deveFalharSeSenhaNovaForNula() {
        Long id = 1L;
        Usuario usuario = Helper.gerarUsuario();
        usuario.setId(id);
        usuario.setSenha("Senha@123");

        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);
        when(passwordEncoder.matches("Senha@123", "Senha@123")).thenReturn(true);

        assertThatThrownBy(() -> useCase.run(id, "Senha@123", null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Senha não pode ser nula!");
    }

    @Test
    void deveFalharSeNaoTiverLetraMaiuscula() {
        assertSenhaFraca("senha@123", "Senha deve conter pelo menos uma letra maiuscula!");
    }

    @Test
    void deveFalharSeNaoTiverNumero() {
        assertSenhaFraca("Senha@aaa", "Senha deve conter pelo menos um número.");
    }

    @Test
    void deveFalharSeNaoTiverCaracterEspecial() {
        assertSenhaFraca("Senha1234", "Senha deve conter pelo menos um caracter especial.");
    }

    @Test
    void deveFalharSeTiverMenosDe8Caracteres() {
        assertSenhaFraca("S@1a", "Senha deve ter no minimo 8 caracteres.");
    }

    @Test
    void deveFalharSeUsuarioNaoForEncontrado() {
        Long id = 99L;

        when(gateway.buscarUsuarioPorIdentificador(id))
                .thenThrow(new EntidadeNaoEncontradaException("Usuário", "ID: " + id));

        assertThatThrownBy(() -> useCase.run(id, "senhaVelha", "Senha@123"))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Usuário")
                .hasMessageContaining("ID: " + id);
    }

    void assertSenhaFraca(String senhaNova, String mensagemEsperada) {
        Long id = 1L;
        Usuario usuario = Helper.gerarUsuario();
        usuario.setId(id);
        usuario.setSenha("Senha@123");

        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);
        when(passwordEncoder.matches("Senha@123", "Senha@123")).thenReturn(true);

        assertThatThrownBy(() -> useCase.run(id, "Senha@123", senhaNova))
                .isInstanceOf(ValidationException.class)
                .hasMessage(mensagemEsperada);

        verify(gateway, never()).atualizarNovaSenhaUsuario(any(), any());
    }
}

