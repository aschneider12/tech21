package br.com.fiap.restaurante.application.gateways;

import br.com.fiap.restaurante.domain.interfaces.storage.IDataStorageUsuario;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UsuarioGatewayTest {

    @Mock
    private IDataStorageUsuario dataSource;

    private UsuarioGateway gateway;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        gateway = UsuarioGateway.create(dataSource);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirCadastrarUsuario(){
        Long id = 1L;
        var usuario = Helper.gerarUsuario();
        usuario.setId(id);

        when(dataSource.cadastrar(usuario)).thenReturn(usuario);


        var usuarioArmazenado = gateway.cadastrar(usuario);

        assertThat(usuarioArmazenado)
                .isNotNull()
                .isInstanceOf(Usuario.class)
                .isEqualTo(usuario);
        verify(dataSource, times(1)).cadastrar(usuario);
    }

    @Test
    void devePermitirAtualizarUsuario(){
        Long id = 1L;
        var usuario = Helper.gerarUsuario();
        usuario.setId(id);

        when(dataSource.atualizar(usuario)).thenReturn(usuario);


        var usuarioAtualizado = gateway.atualizar(usuario);

        assertThat(usuarioAtualizado)
                .isNotNull()
                .isInstanceOf(Usuario.class)
                .isEqualTo(usuario);
        verify(dataSource, times(1)).atualizar(usuario);
    }


    @Test
    void devePermitirDeletarUsuario(){
        var id = 1L;

        when(dataSource.deletar(id)).thenReturn(true);

        boolean usuarioFoiDeletado = gateway.deletar( id);

        assertThat(usuarioFoiDeletado).isTrue();
        verify(dataSource, times(1)).deletar(id);

    }



    @Test
    void devePermitirRetornarTodosOsUsuarios(){
        List<Usuario> usuarios = List.of(
                Helper.gerarUsuario(),
                Helper.gerarUsuario(),
                Helper.gerarUsuario()
        );

        when(dataSource.buscarTodosUsuarios()).thenReturn(usuarios);

        var todosUsuarios = gateway.buscarTodosUsuarios();

        assertThat(todosUsuarios)
                .isNotNull()
                .hasSize(usuarios.size());


        for (int i = 0; i < usuarios.size(); i++) {
            assertThat(todosUsuarios.get(i).getNome())
                    .isEqualTo(usuarios.get(i).getNome());

            assertThat(todosUsuarios.get(i).getEmail())
                    .isEqualTo(usuarios.get(i).getEmail());

            assertThat(todosUsuarios.get(i).getLogin())
                    .isEqualTo(usuarios.get(i).getLogin());
        }

        verify(dataSource, times(1)).buscarTodosUsuarios();

    }

    @Test
    void devePermitirBuscarUsuarioPorId(){
        Long id = 1L;
        var usuario = Helper.gerarUsuario();
        usuario.setId(id);

        when(dataSource.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);

        var usuarioObtido = gateway.buscarUsuarioPorIdentificador(id);

        assertThat(usuarioObtido)
                .isNotNull()
                .isEqualTo(usuario);
        verify(dataSource, times(1)).buscarUsuarioPorIdentificador(id);

    }

    @Test
    void devePermitirConsultarUsuarioPorLogin(){
        Long id = 1L;
        String login = "teste";
        var usuario = Helper.gerarUsuario();
        usuario.setId(id);

        when(dataSource.buscarUsuarioPorLogin(login)).thenReturn(usuario);

        var usuarioObtido = gateway.buscarUsuarioPorLogin(login);

        assertThat(usuarioObtido)
                .isNotNull()
                .isEqualTo(usuario);
        verify(dataSource, times(1)).buscarUsuarioPorLogin(login);

    }


    @Test
    void devePermitirAtualizarSenha(){
        var senhaNova = "$2b$12$KIXgDMC7FQeA3RB.xzOZkeOaJNVJsF7uV.lOS3Hk3rxx4LWFCx1I6";
        var id = 1L;

        doNothing().when(dataSource).atualizarNovaSenha(id, senhaNova);

        gateway.atualizarNovaSenhaUsuario(id, senhaNova);

        verify(dataSource, times(1)).atualizarNovaSenha(id, senhaNova);

    }



}
