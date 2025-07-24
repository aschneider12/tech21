package br.com.fiap.restaurante.core.domain.usecases.itemcardapio;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UseCaseBuscarTodosItemCardapioTest {

    @Mock
    private IItemCardapioGateway gateway;

    private UseCaseBuscarTodosItemCardapio useCase;

    @BeforeEach
    void setUp() {
        useCase = new UseCaseBuscarTodosItemCardapio(gateway);
    }

    @Test
    void deveRetornarListaDeItensCardapio() {
        List<ItemCardapio> listaEsperada = List.of(
            ItemCardapio.create(1L, "Coxinha", "Frango", new BigDecimal("10.00"), "unidade", "/img1.png"),
            ItemCardapio.create(2L, "Pizza", "Calabresa", new BigDecimal("35.00"), "unidade", "/img2.png")
        );

        when(gateway.buscarTodos()).thenReturn(listaEsperada);

        List<ItemCardapio> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Pizza", resultado.get(1).getNome());

        verify(gateway).buscarTodos();
    }
}
