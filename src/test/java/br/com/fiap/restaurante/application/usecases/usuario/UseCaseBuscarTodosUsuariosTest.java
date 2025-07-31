package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.usecases.itemcardapio.UseCaseBuscarTodosItemCardapio;
import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UseCaseBuscarTodosUsuariosTest {

    @Mock
    private IUsuarioGateway gateway;

    private UseCaseBuscarTodosUsuarios useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseBuscarTodosUsuarios.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirBuscarTodosOsUsuarios(){
        var usuario1 =  Helper.gerarUsuario();
        var usuario2 =  Helper.gerarUsuario();
        var usuario3 =  Helper.gerarUsuario();
        usuario1.setId(1L);
        usuario2.setId(2L);
        usuario3.setId(3L);
        List<Usuario> usuarios = List.of(
                usuario1,
                usuario2,
                usuario3
        );

        when(gateway.buscarTodosUsuarios()).thenReturn(usuarios);

        var todosUsuarios = useCase.run();

        assertThat(todosUsuarios)
                .isNotNull()
                .hasSize(usuarios.size());


        for (int i = 0; i < usuarios.size(); i++) {
            assertThat(todosUsuarios.get(i).nome())
                    .isEqualTo(usuarios.get(i).getNome());

            assertThat(todosUsuarios.get(i).id())
                    .isEqualTo(usuarios.get(i).getId());


            assertThat(todosUsuarios.get(i).login())
                    .isEqualTo(usuarios.get(i).getLogin());
        }

        verify(gateway, times(1)).buscarTodosUsuarios();
    }

    @Test
    void deveRetornarListaVazioCasoNaoExistaUsuarios(){

        when(gateway.buscarTodosUsuarios()).thenReturn(List.of());

        var todosUsuarios = useCase.run();

        assertThat(todosUsuarios)
                .isEmpty();

        verify(gateway, times(1)).buscarTodosUsuarios();
    }

    @Test
    void deveRetornarListaNuloCasoRetornoDeTodosOsUsuariosVenhaNulo(){

        when(gateway.buscarTodosUsuarios()).thenReturn(null);

        var todosUsuarios = useCase.run();

        assertThat(todosUsuarios)
                .isEmpty();

        verify(gateway, times(1)).buscarTodosUsuarios();
    }
}
