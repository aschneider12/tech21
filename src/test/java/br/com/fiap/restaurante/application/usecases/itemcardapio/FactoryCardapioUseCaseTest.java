package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryCardapioUseCaseTest {

    @Mock
    private IItemCardapioGateway gateway;

    private FactoryCardapioUseCase factory;

    @BeforeEach
    void setUp() {
        factory = new FactoryCardapioUseCase(gateway);
    }

    @Test
    void devePermtirCriarUseCaseAtualizarItemCardapio() {
        var useCase = factory.atualizarItemCardapio();
        assertThat(useCase)
                .isNotNull()
                .isInstanceOf(UseCaseAtualizarItemCardapio.class);
    }

    @Test
    void devePermtirCriarUseCaseBuscarItemCardapioPorID() {
        var useCase = factory.buscarItemCardapioPorID();
        assertThat(useCase)
                .isNotNull()
                .isInstanceOf(UseCaseBuscarItemCardapioPorID.class);
    }

    @Test
    void devePermtirCriarUseCaseBuscarTodosItemCardapio() {
        var useCase = factory.buscarTodosItemCardapio();
        assertThat(useCase)
                .isNotNull()
                .isInstanceOf(UseCaseBuscarTodosItemCardapio.class);
    }

    @Test
    void devePermtirCriarUseCaseCadastrarItemCardapio() {
        var useCase = factory.cadastrarItemCardapio();
        assertThat(useCase)
                .isNotNull()
                .isInstanceOf(UseCaseCadastrarItemCardapio.class);
    }

    @Test
    void devePermtirCriarUseCaseDeletarItemCardapio() {
        var useCase = factory.deletarItemCardapio();
        assertThat(useCase)
                .isNotNull()
                .isInstanceOf(UseCaseDeletarItemCardapio.class);
    }
}
