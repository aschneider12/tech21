package br.com.fiap.restaurante.core.presenters;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCardapioPresenterTest {

    @Test
    void deveConverterItemCardapioParaDTO() {
        // Cria
        ItemCardapio item = ItemCardapio.create(
            1L,
            "Pastel",
            "Carne",
            new BigDecimal("8.50"),
            "unidade",
            "/img/pastel.png"
        );

        // Executa
        ItemCardapioOutputDTO dto = ItemCardapioPresenter.toDTO(item);

        // Verifica
        assertNotNull(dto);
        assertEquals(1L, dto.id());
        assertEquals("Pastel", dto.nome());
        assertEquals("Carne", dto.descricao());
        assertEquals(new BigDecimal("8.50"), dto.preco());
        assertEquals("unidade", dto.tipoVenda());
        assertEquals("/img/pastel.png", dto.pathFoto());
    }
}
