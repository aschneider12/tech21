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
public class UseCaseCadastrarItemCardapioTest {

    @Mock
    private IItemCardapioGateway gateway;

    private UseCaseCadastrarItemCardapio useCase;

    @BeforeEach
    void setUp() {
        useCase = new UseCaseCadastrarItemCardapio(gateway);
    }

    @Test
    void deveCadastrarItemComSucesso() {
        // Preparação dos dados e mocks
        ItemCardapio novoItem = ItemCardapio.create("Coxinha", "Frango com catupiry", new BigDecimal("10.00"), "unidade", "/coxinha.png");
        ItemCardapio itemSalvo = ItemCardapio.create(1L, "Coxinha", "Frango com catupiry", new BigDecimal("10.00"), "unidade", "/coxinha.png");

        when(gateway.cadastrar(novoItem)).thenReturn(itemSalvo);

        // Execução da ação testada
        ItemCardapio resultado = useCase.execute(novoItem);

        // Resultados
        assertNotNull(resultado);
        assertEquals("Coxinha", resultado.getNome());
        assertEquals(new BigDecimal("10.00"), resultado.getPreco());

        verify(gateway).cadastrar(novoItem);
    }
}

