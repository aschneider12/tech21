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

public class UseCaseAdicionarPerfisUsuarioTest {

    @Mock
    private IUsuarioGateway gateway;

    private UseCaseAdicionarPerfisUsuario useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseAdicionarPerfisUsuario.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirAdicionarPerfisAoUsuarioQueJaTemAlgumPerfil(){

        Long id = 1L;
        Set<String>  perfis = new HashSet<String>();
        perfis.add("dono");
        perfis.add("admin");

        var usuario = Helper.gerarUsuario();
        usuario.setId(1L);


        usuario.setPerfis(new HashSet<>(Set.of("cliente")));

        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);
        
        
        useCase.run(id, perfis);

        Set<String> perfisEsperados = Set.of("cliente", "dono", "admin");

        assertThat(usuario.getPerfis())
                .containsExactlyInAnyOrderElementsOf(perfisEsperados);
        verify(gateway, times(1)).atualizar(usuario);

    }

    @Test
    void devePermitirAdicionarPerfisAoUsuarioQueNaoPossuiPerfisAinda(){

        Long id = 1L;
        Set<String>  perfis = new HashSet<String>();
        perfis.add("dono");
        perfis.add("admin");

        var usuario = Helper.gerarUsuario();
        usuario.setId(1L);

        usuario.setPerfis(null);

        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);


        useCase.run(id, perfis);

        Set<String> perfisEsperados = Set.of("dono", "admin");

        assertThat(usuario.getPerfis())
                .containsExactlyInAnyOrderElementsOf(perfisEsperados);
        verify(gateway, times(1)).atualizar(usuario);

    }

    @Test
    void deveFalharAoTentarAdicionarSetDePerfisVaziosAoUsuario(){
        Long id = 1L;
        Set<String>  perfis = new HashSet<String>();

        var usuario = Helper.gerarUsuario();
        usuario.setId(1L);

        usuario.setPerfis(new HashSet<>(Set.of("cliente")));


        assertThatThrownBy(() -> useCase.run( id, perfis))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Nenhum perfil informado para ser adicionado.");
        verify(gateway,never()).buscarUsuarioPorIdentificador(id);
        verify(gateway, never()).atualizar(usuario);
    }

    @Test
    void deveFalharAoTentarAdicionarSetDePerfisNuloAoUsuario(){
        Long id = 1L;
        Set<String>  perfis = null;

        var usuario = Helper.gerarUsuario();
        usuario.setId(1L);

        usuario.setPerfis(new HashSet<>(Set.of("cliente")));


        assertThatThrownBy(() -> useCase.run( id, perfis))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Nenhum perfil informado para ser adicionado.");
        verify(gateway,never()).buscarUsuarioPorIdentificador(id);
        verify(gateway, never()).atualizar(usuario);
    }
}
