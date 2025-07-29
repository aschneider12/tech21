package br.com.fiap.restaurante.application.usecases.restaurante;

import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.database.entities.RestauranteEntity;
import br.com.fiap.restaurante.infra.database.mappers.RestauranteEntityMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UseCaseBuscarTodosRestaurantesTest {

    @Mock
    private IRestauranteGateway gateway;

    private UseCaseBuscarTodosRestaurantes useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseBuscarTodosRestaurantes.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirRetornarTodosOsRestaurantes(){
        List<Restaurante> restaurantes = List.of(
                Helper.gerarRestaurante(),
                Helper.gerarRestaurante(),
                Helper.gerarRestaurante()
        );


        when(gateway.buscarTodosRestaurantes()).thenReturn(restaurantes);

        var todosRestaurantes = useCase.run();

        assertThat(todosRestaurantes)
                .isNotNull()
                .hasSize(restaurantes.size());


        for (int i = 0; i < restaurantes.size(); i++) {
            assertThat(todosRestaurantes.get(i).nome())
                    .isEqualTo(restaurantes.get(i).getNome());

            assertThat(todosRestaurantes.get(i).tipoCozinha())
                    .isEqualTo(restaurantes.get(i).getTipoCozinha());
        }

        verify(gateway, times(1)).buscarTodosRestaurantes();

    }

}
