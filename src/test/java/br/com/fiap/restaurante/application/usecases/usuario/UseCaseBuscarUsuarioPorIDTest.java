package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.application.usecases.restaurante.UseCaseBuscarRestaurantePorID;
import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UseCaseBuscarUsuarioPorIDTest {

    @Mock
    private IUsuarioGateway gateway;

    private UseCaseBuscarUsuarioPorID useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseBuscarUsuarioPorID.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirBuscarUsuarioPorId(){
        Long id = 1L;
        var usuario = Helper.gerarUsuario();
        usuario.setId(id);

        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);

        UsuarioOutput usuarioRetornado = useCase.run(id);

        assertThat(usuarioRetornado)
                .isNotNull();
        assertThat(usuarioRetornado.nome())
                .isEqualTo(usuario.getNome());
        assertThat(usuarioRetornado.id())
                .isEqualTo(id);
        verify(gateway, times(1)).buscarUsuarioPorIdentificador(id);
    }
}
