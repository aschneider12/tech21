package br.com.fiap.restaurante.application.usecases.usuario.perfil;

import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class UseCaseRemoverPerfisUsuarioTest {

    @Mock
    private IUsuarioGateway gateway;

    private UseCaseRemoverPerfisUsuario useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseRemoverPerfisUsuario.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirRemoverPerfisDoUsuarioQueJaTemAlgumPerfil(){

        Long id = 1L;
        Set<String> perfisASeremDeletados = new HashSet<String>();
        perfisASeremDeletados.add("dono");
        perfisASeremDeletados.add("admin");

        var usuario = Helper.gerarUsuario();
        usuario.setId(1L);

        usuario.setPerfis(new HashSet<>(Set.of("cliente", "dono", "admin")));

        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);

        useCase.run(id, perfisASeremDeletados);

        Set<String> perfisEsperados = Set.of("cliente");


        assertThat(usuario.getPerfis())
                .containsExactlyInAnyOrderElementsOf(perfisEsperados);
        verify(gateway, times(1)).atualizar(usuario);

    }


    @Test
    void deveFalharAoPassarSetDePerfisVaziosAoUsuarioParaRemocao(){
        Long id = 1L;
        Set<String>  perfisASeremDeletados = new HashSet<String>();

        var usuario = Helper.gerarUsuario();
        usuario.setId(1L);

        usuario.setPerfis(new HashSet<>(Set.of("cliente")));


        assertThatThrownBy(() -> useCase.run( id, perfisASeremDeletados))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Nenhum perfil informado para ser removido.");
        verify(gateway,never()).buscarUsuarioPorIdentificador(id);
        verify(gateway, never()).atualizar(usuario);
    }

    @Test
    void deveFalharAoPassarSetDePerfisNuloAoUsuarioParaRemocao(){
        Long id = 1L;
        Set<String>  perfisASeremDeletados = null;

        var usuario = Helper.gerarUsuario();
        usuario.setId(1L);

        usuario.setPerfis(new HashSet<>(Set.of("cliente")));


        assertThatThrownBy(() -> useCase.run( id, perfisASeremDeletados))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Nenhum perfil informado para ser removido.");
        verify(gateway,never()).buscarUsuarioPorIdentificador(id);
        verify(gateway, never()).atualizar(usuario);
    }

    @Test
    void deveFalharAoTentarRemoverPerfisDeUsuarioQueNaoPossuiPerfis(){
        Long id = 1L;
        Set<String> perfisASeremDeletados = new HashSet<String>();
        perfisASeremDeletados.add("dono");
        perfisASeremDeletados.add("admin");

        var usuario = Helper.gerarUsuario();
        usuario.setId(1L);

        usuario.setPerfis(null);
        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);

        assertThatThrownBy(() -> useCase.run( id, perfisASeremDeletados))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Usuário não possui perfis para serem removidos");
        verify(gateway,times(1)).buscarUsuarioPorIdentificador(id);
        verify(gateway, never()).atualizar(usuario);
    }
}
