package br.com.fiap.restaurante.application.input;

import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.models.Restaurante;

import java.math.BigDecimal;

public record ItemCardapioInput (
        String nome,
        String descricao,
        BigDecimal preco,
        String tipoVenda,
        Long restauranteId,
        String pathFoto
) {
    public static ItemCardapio toDomain(ItemCardapioInput input) {

        return ItemCardapio.create(input.nome(), input.descricao(),
                input.preco(), input.tipoVenda(), new Restaurante(input.restauranteId()),
                input.pathFoto());
    }

}
