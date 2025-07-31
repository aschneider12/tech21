package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.models.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UseCaseDeletarItemCardapioTest {

    @Mock
    private IItemCardapioGateway gateway;

    private UseCaseDeletarItemCardapio useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = UseCaseDeletarItemCardapio.create(gateway);
    }

    @Test
    void deveDeletarItemCardapioQuandoPertenceAoRestaurante() {
        // Dados simulados
        Long restauranteId = 1L;
        Long itemId = 10L;
        Restaurante restaurante = new Restaurante(restauranteId);

        ItemCardapio item = ItemCardapio.create(
            itemId,
            "Pizza",
            "Pizza de Calabresa",
            new BigDecimal("39.90"),
            "unidade",
            restaurante,
            "/img/pizza.png"
        );

        // Configura comportamento do mock
        when(gateway.buscarItemCardapioPorIdentificador(itemId)).thenReturn(item);
        when(gateway.deletar(itemId)).thenReturn(true);

        // Executa o use case
        boolean resultado = useCase.run(restauranteId, itemId);

        // Verificações
        assertTrue(resultado);
        verify(gateway, times(1)).buscarItemCardapioPorIdentificador(itemId);
        verify(gateway, times(1)).deletar(itemId);
    }
}

