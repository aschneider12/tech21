package br.com.fiap.restaurante.application.output;

import br.com.fiap.restaurante.domain.models.ItemCardapio;

import java.math.BigDecimal;
import java.util.List;

public record ItemCardapioOutput(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        String tipoVenda,
        RestauranteOutput restauranteOutput,
        String pathFoto
) {

    public static ItemCardapioOutput create(
            Long id,
            String nome,
            String descricao,
            BigDecimal preco,
            String tipoVenda,
            RestauranteOutput restaurante,
            String pathFoto
){

        return new ItemCardapioOutput(id, nome, descricao, preco, tipoVenda,
                restaurante, pathFoto);
    }

    public static ItemCardapioOutput fromDomain(ItemCardapio i) {
        if(i != null)
            return new ItemCardapioOutput(i.getId(), i.getNome(), i.getDescricao(),
                    i.getPreco(), i.getTipoVenda(),
                    RestauranteOutput.fromDomainNoRelations(i.getRestaurante()),
                    i.getPathFoto());

        return null;
    }

    public static List<ItemCardapioOutput> fromDomain(List<ItemCardapio> itensCardapio) {
        if(itensCardapio != null)
            return itensCardapio.stream().map(ItemCardapioOutput::fromDomain).toList();

        return List.of();
    }
}
