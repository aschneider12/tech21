package br.com.fiap.restaurante.infra.database.repositories.adapter;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.mappers.UsuarioEntityMapper;
import br.com.fiap.restaurante.infra.database.repositories.jpa.UsuarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UsuarioRepositoryAdapterTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    private UsuarioRepositoryAdapter adapter;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        adapter = new UsuarioRepositoryAdapter(usuarioRepository);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }


    @Test
    void devePermitirAtualizarSenha(){
        var senhaNova = "$2b$12$KIXgDMC7FQeA3RB.xzOZkeOaJNVJsF7uV.lOS3Hk3rxx4LWFCx1I6";
        var id = 1L;

        doNothing().when(usuarioRepository).updateNovaSenha(any(String.class), any(Long.class));

        adapter.atualizarNovaSenha(id, senhaNova);

        verify(usuarioRepository, times(1)).updateNovaSenha(senhaNova, id);

    }

    @Test
    void deveVerificarSeUsuarioExistePorLogin() {
        var login = "admin";

        when(usuarioRepository.existsByLogin(any(String.class))).thenReturn(true);

        var usuarioExistente = adapter.existsByLogin("login");

        assertThat(usuarioExistente)
                .isTrue();
    }

    @Test
    void deveVerificarSeUsuarioExistePorEmail() {
        var email = "admin@fiap.com";

        when(usuarioRepository.existsByEmail(any(String.class))).thenReturn(true);

        var usuarioExistente = adapter.existsByEmail(email);

        assertThat(usuarioExistente)
                .isTrue();

    }

    @Test
    void devePermitirCadastrarUsuario(){
        Long id = 1L;
        var usuario = Helper.gerarUsuario();
        usuario.setId(id);
        var usuarioEntity = UsuarioEntityMapper.INSTANCE.toEntity(usuario);

        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);
        var usuarioArmazenado = adapter.cadastrar(usuario);

        assertThat(usuarioArmazenado)
                .isNotNull()
                .isInstanceOf(Usuario.class)
                .isEqualTo(usuario);
        verify(usuarioRepository, times(1)).save(usuarioEntity);
    }

    @Test
    void devePermitirAtualizarUsuario(){
        Long id = 1L;
        var usuario = Helper.gerarUsuario();
        usuario.setId(id);
        var usuarioEntity = UsuarioEntityMapper.INSTANCE.toEntity(usuario);

        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);
        var usuarioAtualizado = adapter.atualizar(usuario);

        assertThat(usuarioAtualizado)
                .isNotNull()
                .isInstanceOf(Usuario.class)
                .isEqualTo(usuario);
        verify(usuarioRepository, times(1)).save(usuarioEntity);
    }

    @Nested
    class DeletarUsuario{
        @Test
        void devePermitirDeletarUsuario(){
            var id = 1L;

            when(usuarioRepository.existsById(any(Long.class))).thenReturn(true);
            doNothing().when(usuarioRepository).deleteById(any(Long.class));

            boolean usuarioFoiDeletado = adapter.deletar( id);

            assertThat(usuarioFoiDeletado).isTrue();
            verify(usuarioRepository, times(1)).deleteById(id);
            verify(usuarioRepository, times(1)).existsById(id);

        }

        @Test
        void deveRetornarFalsoAoTentarDeletarUsuarioQueNaoExiste(){
            var id = 1L;

            when(usuarioRepository.existsById(any(Long.class))).thenReturn(false);

            boolean usuarioFoiDeletado = adapter.deletar( id);

            assertThat(usuarioFoiDeletado).isFalse();
            verify(usuarioRepository, times(1)).existsById(id);
        }

    }

    @Nested
    class ConsultarUsuarioPorLogin{
        @Test
        void devePermitirConsultarUsuarioPorLogin(){
            Long id = 1L;
            String login = "teste";
            var usuario = Helper.gerarUsuario();
            usuario.setId(id);
            var usuarioEntity = UsuarioEntityMapper.INSTANCE.toEntity(usuario);

            when(usuarioRepository.findByLogin(any(String.class))).thenReturn(Optional.of(usuarioEntity));

            var usuarioObtido = adapter.buscarUsuarioPorLogin(login);

            assertThat(usuarioObtido)
                    .isNotNull()
                    .isEqualTo(usuario);
            verify(usuarioRepository, times(1)).findByLogin(login);

        }

        @Test
        void deveRetornarNuloCasoNaoEncontreUsuarioPorLogin(){
            String login = "teste";
            when(usuarioRepository.findByLogin(any(String.class))).thenReturn(Optional.empty());

            var restauranteObtido = adapter.buscarUsuarioPorLogin(login);

            assertThat(restauranteObtido)
                    .isNull();
            verify(usuarioRepository, times(1)).findByLogin(login);

        }

    }


    @Nested
    class ConsultarUsuarioPorId{
        @Test
        void devePermitirConsultarUsuarioPorId(){
            Long id = 1L;
            var usuario = Helper.gerarUsuario();
            usuario.setId(id);
            var usuarioEntity = UsuarioEntityMapper.INSTANCE.toEntity(usuario);

            when(usuarioRepository.findById(any(Long.class))).thenReturn(Optional.of(usuarioEntity));

            var usuarioObtido = adapter.buscarUsuarioPorIdentificador(id);

            assertThat(usuarioObtido)
                    .isNotNull()
                    .isEqualTo(usuario);
            verify(usuarioRepository, times(1)).findById(id);

        }

        @Test
        void deveFalharCasoNaoEncontreUsuarioPorId(){
            Long id = 1L;
            when(usuarioRepository.findById(any(Long.class))).thenReturn(Optional.empty());

            assertThatThrownBy(() -> adapter.buscarUsuarioPorIdentificador(id))
                    .isInstanceOf(EntidadeNaoEncontradaException.class)
                    .hasMessageContaining("Usuário")
                    .hasMessageContaining("ID:")
                    .hasMessageContaining(String.valueOf(id));


        }
    }


    @Test
    void devePermitirRetornarTodosOsUsuarios(){
        List<UsuarioEntity> usuariosEntity = List.of(
                Helper.gerarUsuarioEntity(),
                Helper.gerarUsuarioEntity(),
                Helper.gerarUsuarioEntity()
        );

        when(usuarioRepository.findAll()).thenReturn(usuariosEntity);

        var todosUsuarios = adapter.buscarTodosUsuarios();

        assertThat(todosUsuarios)
                .isNotNull()
                .hasSize(usuariosEntity.size());


        for (int i = 0; i < usuariosEntity.size(); i++) {
            assertThat(todosUsuarios.get(i).getNome())
                    .isEqualTo(usuariosEntity.get(i).getNome());

            assertThat(todosUsuarios.get(i).getEmail())
                    .isEqualTo(usuariosEntity.get(i).getEmail());

            assertThat(todosUsuarios.get(i).getLogin())
                    .isEqualTo(usuariosEntity.get(i).getLogin());
        }

        verify(usuarioRepository, times(1)).findAll();

    }

}
