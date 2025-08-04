package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.application.input.EnderecoInput;
import br.com.fiap.restaurante.application.input.UsuarioInput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.mappers.UsuarioEntityMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UseCaseCadastrarUsuarioTest {

    @Mock
    private IUsuarioGateway gateway;
    @Mock
    private PasswordEncoder passwordEncoder;


    private UseCaseCadastrarUsuario useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseCadastrarUsuario.create(gateway, passwordEncoder);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirCadastrarUsuario(){

        String login = "teste";

        var usuario = Helper.gerarUsuario();
        var usuarioInput = UsuarioInput.create(
            usuario.getNome(),
            usuario.getEmail(),
            login,
            usuario.getSenha(),
            usuario.getPerfis(),
            EnderecoInput.create(
                usuario.getEndereco().getId(),
                usuario.getEndereco().getRua(),
                usuario.getEndereco().getNumero(),
                usuario.getEndereco().getCidade(),
                usuario.getEndereco().getEstado(),
                usuario.getEndereco().getCep()
            )
        );
        usuario.setId(1L);

        when(gateway.buscarUsuarioPorLogin(login)).thenReturn(null);
        when(gateway.cadastrar(UsuarioInput.toDomain(usuarioInput))).thenReturn(usuario);

        UsuarioOutput usuarioCadastrado = useCase.run(usuarioInput);

        assertThat(usuarioCadastrado)
                .isNotNull()
                .isInstanceOf(UsuarioOutput.class);
        assertThat(usuarioCadastrado.nome()).isEqualTo(usuario.getNome());
        assertThat(usuarioCadastrado.login()).isEqualTo(usuario.getLogin());
        assertThat(usuarioCadastrado.email()).isEqualTo(usuario.getEmail());
        assertThat(usuarioCadastrado.id()).isEqualTo(usuario.getId());
        verify(gateway, times(1)).cadastrar(UsuarioInput.toDomain(usuarioInput));
        verify(gateway, times(1)).buscarUsuarioPorLogin(login);
    }

    @Test
    void deveFalharAoCadastrarUsuarioQueJaExiste(){

        String login = "teste";

        var usuario = Helper.gerarUsuario();
        var usuarioInput = UsuarioInput.create(
                usuario.getNome(),
                usuario.getEmail(),
                login,
                usuario.getSenha(),
                usuario.getPerfis(),
                EnderecoInput.create(
                        usuario.getEndereco().getId(),
                        usuario.getEndereco().getRua(),
                        usuario.getEndereco().getNumero(),
                        usuario.getEndereco().getCidade(),
                        usuario.getEndereco().getEstado(),
                        usuario.getEndereco().getCep()
                )
        );
        usuario.setId(1L);

        when(gateway.buscarUsuarioPorLogin(login)).thenReturn(usuario);

        assertThatThrownBy(() -> useCase.run(usuarioInput))
                .isInstanceOf(EntidadeJaExisteException.class)
                .hasMessageContaining("Usuário")
                .hasMessageContaining(usuarioInput.login());
        verify(gateway, times(1)).buscarUsuarioPorLogin(login);
        verify(gateway, never()).cadastrar(UsuarioInput.toDomain(usuarioInput));
    }

    @Test
    void deveFalharAoTentarCadastrarUsuariosComDadosInvalidos(){
        String login = "teste";

        var usuario = Helper.gerarUsuario();
        usuario.setSenha("1234");
        var usuarioInput = UsuarioInput.create(
                usuario.getNome(),
                usuario.getEmail(),
                login,
                usuario.getSenha(),
                usuario.getPerfis(),
                EnderecoInput.create(
                        usuario.getEndereco().getId(),
                        usuario.getEndereco().getRua(),
                        usuario.getEndereco().getNumero(),
                        usuario.getEndereco().getCidade(),
                        usuario.getEndereco().getEstado(),
                        usuario.getEndereco().getCep()
                )
        );
        usuario.setId(1L);

        when(gateway.buscarUsuarioPorLogin(login)).thenReturn(null);

        assertThatThrownBy(() -> useCase.run(usuarioInput))
                .isInstanceOf(IllegalArgumentException.class);
        verify(gateway, times(1)).buscarUsuarioPorLogin(login);
        verify(gateway, never()).cadastrar(UsuarioInput.toDomain(usuarioInput));
    }

}
