package br.com.fiap.restaurante.core.dtos.itemcardapio;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;

import java.math.BigDecimal;

public record ItemCardapioInputDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        String tipoVenda,
        String pathFoto
) {
    public static ItemCardapioInputDTO  fromEntity(ItemCardapio itemCardapio) {
        return new ItemCardapioInputDTO(
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getTipoVenda(),
                itemCardapio.getPathFoto()

        );
    }

}
