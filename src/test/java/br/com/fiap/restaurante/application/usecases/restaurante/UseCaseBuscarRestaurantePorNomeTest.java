package br.com.fiap.restaurante.application.usecases.restaurante;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class UseCaseBuscarRestaurantePorNomeTest {

    @Mock
    private IRestauranteGateway gateway;

    private UseCaseBuscarRestaurantePorNome useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = new UseCaseBuscarRestaurantePorNome(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }


    @Test
    void devePermitirBuscarRestaurantePorNome(){
        Long id = 1L;
        String nome = "Restaurante teste";
        var restaurante = Helper.gerarRestaurante();
        restaurante.setId(id);

        when(gateway.buscarRestaurantePorNome(nome)).thenReturn(restaurante);

        RestauranteOutput restauranteRetornado = useCase.run(nome);

        assertThat(restauranteRetornado)
                .isNotNull();
        assertThat(restauranteRetornado.nome())
                .isEqualTo(nome);
        assertThat(restauranteRetornado.id())
                .isEqualTo(id);
        verify(gateway, times(1)).buscarRestaurantePorNome(nome);
    }

    @Test
    void deveFalharAoBuscarRestaurantePorNomeQueNaoExiste(){

        String nome = "Restaurante teste";

        when(gateway.buscarRestaurantePorNome(nome)).thenReturn(null);

        assertThatThrownBy(() -> useCase.run(nome))
                .isInstanceOf(EntidadeNaoEncontradaException.class)
                .hasMessageContaining("Restaurante")
                .hasMessageContaining(nome);
        verify(gateway, times(1)).buscarRestaurantePorNome(nome);
    }
}
