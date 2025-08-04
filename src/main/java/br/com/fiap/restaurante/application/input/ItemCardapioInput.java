package br.com.fiap.restaurante.application.input;

import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.models.Restaurante;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemCardapioInput that = (ItemCardapioInput) o;
        return Objects.equals(nome, that.nome) && Objects.equals(pathFoto, that.pathFoto) && Objects.equals(descricao, that.descricao) && Objects.equals(preco, that.preco) && Objects.equals(tipoVenda, that.tipoVenda) && Objects.equals(restauranteId, that.restauranteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao, preco, tipoVenda, restauranteId, pathFoto);
    }
}
