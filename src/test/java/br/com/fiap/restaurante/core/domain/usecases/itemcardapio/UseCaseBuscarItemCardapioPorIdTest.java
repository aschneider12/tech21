package br.com.fiap.restaurante.core.domain.usecases.itemcardapio;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UseCaseBuscarItemCardapioPorIdTest {

    @Mock
    private IItemCardapioGateway gateway;

    private UseCaseBuscarItemCardapioPorId useCase;

    @BeforeEach
    void setUp() {
        useCase = new UseCaseBuscarItemCardapioPorId(gateway);
    }

    @Test
    void deveRetornarItemCardapioQuandoIdExistir() {
        Long id = 1L;

        ItemCardapio esperado = ItemCardapio.create(
            id, "Pizza", "Calabresa", new BigDecimal("35.00"), "unidade", "/pizza.png"
        );

        when(gateway.buscarPorId(id)).thenReturn(esperado);

        ItemCardapio resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals("Pizza", resultado.getNome());
        assertEquals(new BigDecimal("35.00"), resultado.getPreco());

        verify(gateway).buscarPorId(id);
    }

    @Test
    void deveRetornarNullQuandoItemNaoExistir() {
        Long id = 999L;
        when(gateway.buscarPorId(id)).thenReturn(null);

        ItemCardapio resultado = useCase.execute(id);

        assertNull(resultado);
        verify(gateway).buscarPorId(id);
    }
}
