package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.input.EnderecoInput;
import br.com.fiap.restaurante.application.input.UsuarioInput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class UseCaseAtualizarUsuarioTest {

    @Mock
    private IUsuarioGateway gateway;

    private UseCaseAtualizarUsuario useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseAtualizarUsuario.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirAtualizarUsuario(){

        Long id = 1L;

        var usuario = Helper.gerarUsuario();
        var usuarioInput = UsuarioInput.create(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
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
        usuario.setId(id);

        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);
        when(gateway.atualizar(UsuarioInput.toDomain(usuarioInput))).thenReturn(usuario);

        UsuarioOutput usuarioAtualizado = useCase.run(id, usuarioInput);

        assertThat(usuarioAtualizado)
                .isNotNull()
                .isInstanceOf(UsuarioOutput.class);
        assertThat(usuarioAtualizado.nome()).isEqualTo(usuario.getNome());
        assertThat(usuarioAtualizado.login()).isEqualTo(usuario.getLogin());
        assertThat(usuarioAtualizado.email()).isEqualTo(usuario.getEmail());
        assertThat(usuarioAtualizado.id()).isEqualTo(usuario.getId());
        verify(gateway, times(1)).atualizar(UsuarioInput.toDomain(usuarioInput));
        verify(gateway, times(1)).buscarUsuarioPorIdentificador(id);
    }

    @Test
    void deveFalharAoTentarAtualizarUsuarioQueNaoExiste(){

        Long id = 1L;

        var usuario = Helper.gerarUsuario();
        var usuarioInput = UsuarioInput.create(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
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
        usuario.setId(id);

        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(null);

        assertThatThrownBy(() -> useCase.run(id, usuarioInput))
                .isInstanceOf(EntidadeNaoEncontradaException.class)
                .hasMessageContaining("Usuário")
                .hasMessageContaining("ID")
                .hasMessageContaining(String.valueOf(id));
        verify(gateway, times(1)).buscarUsuarioPorIdentificador(id);
        verify(gateway, never()).atualizar(UsuarioInput.toDomain(usuarioInput));
    }

}
