package br.com.fiap.restaurante.application.usecases.restaurante;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UseCaseBuscarRestaurantePorIdTest {

    @Mock
    private IRestauranteGateway gateway;

    private UseCaseBuscarRestaurantePorID useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseBuscarRestaurantePorID.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }


    @Test
    void devePermitirBuscarRestaurantePorId(){
        Long id = 1L;
        var restaurante = Helper.gerarRestaurante();
        restaurante.setId(id);

        when(gateway.buscarRestaurantePorIdentificador(id)).thenReturn(restaurante);

        RestauranteOutput restauranteRetornado = useCase.run(id);

        assertThat(restauranteRetornado)
                .isNotNull();
        assertThat(restauranteRetornado.nome())
                .isEqualTo(restaurante.getNome());
        assertThat(restauranteRetornado.id())
                .isEqualTo(id);
        verify(gateway, times(1)).buscarRestaurantePorIdentificador(id);
    }

    @Test
    void deveFalharAoBuscarRestaurantePorIdQueNaoExiste(){

        Long id = 1L;

        when(gateway.buscarRestaurantePorIdentificador(id)).thenReturn(null);

        assertThatThrownBy(() -> useCase.run(id))
                .isInstanceOf(EntidadeNaoEncontradaException.class)
                .hasMessageContaining("Restaurante")
                .hasMessageContaining(String.valueOf(id));
        verify(gateway, times(1)).buscarRestaurantePorIdentificador(id);
    }
}
