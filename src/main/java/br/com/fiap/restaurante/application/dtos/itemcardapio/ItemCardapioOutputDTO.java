package br.com.fiap.restaurante.application.dtos.itemcardapio;

import br.com.fiap.restaurante.application.dtos.restaurante.RestauranteOutputDTO;

import java.math.BigDecimal;

public record ItemCardapioOutputDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        String tipoVenda,
        RestauranteOutputDTO restauranteOutputDTO,
        String pathFoto
) {

    public static ItemCardapioOutputDTO create(
            Long id,
            String nome,
            String descricao,
            BigDecimal preco,
            String tipoVenda,
            RestauranteOutputDTO restaurante,
            String pathFoto
){

        return new ItemCardapioOutputDTO(id, nome, descricao, preco, tipoVenda,
                restaurante, pathFoto);
    }


}
