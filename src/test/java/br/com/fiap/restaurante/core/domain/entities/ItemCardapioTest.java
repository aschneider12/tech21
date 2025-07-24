package br.com.fiap.restaurante.core.domain.entities;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCardapioTest {

    @Test
    void deveCriarItemCardapioComTodosOsCampos() {
        Long id = 10L;
        String nome = "Suco";
        String descricao = "Suco de laranja natural";
        BigDecimal preco = new BigDecimal("6.50");
        String tipoVenda = "unidade";
        String pathFoto = "/img/suco.png";

        ItemCardapio item = ItemCardapio.create(id, nome, descricao, preco, tipoVenda, pathFoto);

        assertEquals(id, item.getId());
        assertEquals(nome, item.getNome());
        assertEquals(descricao, item.getDescricao());
        assertEquals(preco, item.getPreco());
        assertEquals(tipoVenda, item.getTipoVenda());
        assertEquals(pathFoto, item.getPathFoto());
    }

    @Test
    void deveCriarItemCardapioSemId() {
        ItemCardapio item = ItemCardapio.create("Bolo", "Bolo de chocolate", new BigDecimal("8.00"), "fatia", "/img/bolo.png");

        assertNull(item.getId());
        assertEquals("Bolo", item.getNome());
    }
}
