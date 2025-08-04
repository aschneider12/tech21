package br.com.fiap.restaurante.application.usecases.restaurante;

import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryRestauranteUseCaseTest {

    @Mock
    private IRestauranteGateway gateway;

    private FactoryRestauranteUseCase factory;

    @BeforeEach
    void setUp() {
        factory = new FactoryRestauranteUseCase(gateway);
    }

    @Test
    void devePermtirCriarUseCaseBuscarTodosRestaurantes() {
        var useCase = factory.buscarTodosRestaurantes();
        assertThat(useCase)
                .isNotNull()
                .isInstanceOf(UseCaseBuscarTodosRestaurantes.class);
    }

    @Test
    void devePermtirCriarUseCaseBuscarRestaurantePorId() {
        var useCase = factory.buscarRestaurantePorId();
        assertThat(useCase)
                .isNotNull()
                .isInstanceOf(UseCaseBuscarRestaurantePorID.class);
    }

    @Test
    void devePermtirCriarUseCaseDeletarRestaurante() {
        var useCase = factory.deletarRestaurante();
        assertThat(useCase)
                .isNotNull()
                .isInstanceOf(UseCaseDeletarRestaurante.class);
    }

    @Test
    void devePermtirCriarUseCaseCadastrarRestaurante() {
        var useCase = factory.cadastrarRestaurante();
        assertThat(useCase)
                .isNotNull()
                .isInstanceOf(UseCaseCadastrarRestaurante.class);
    }

    @Test
    void devePermtirCriarUseCaseAtualizarRestaurante() {
        var useCase = factory.atualizarRestaurante();
        assertThat(useCase)
                .isNotNull()
                .isInstanceOf(UseCaseAtualizarRestaurante.class);
    }
}
