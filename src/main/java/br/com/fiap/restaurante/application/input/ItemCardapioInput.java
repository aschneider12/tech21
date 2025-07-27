package br.com.fiap.restaurante.application.input;

import br.com.fiap.restaurante.domain.models.ItemCardapio;

import java.math.BigDecimal;

public record ItemCardapioInput (
        String nome,
        String descricao,
        BigDecimal preco,
        String tipoVenda,
        String pathFoto
) {
    public static ItemCardapioInput  fromEntity(ItemCardapio itemCardapio) {
        return new ItemCardapioInput(
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getTipoVenda(),
                itemCardapio.getPathFoto()

        );
    }

}
