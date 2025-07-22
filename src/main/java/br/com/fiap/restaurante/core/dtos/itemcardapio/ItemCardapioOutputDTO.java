package br.com.fiap.restaurante.core.dtos.itemcardapio;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;

import java.math.BigDecimal;

public record ItemCardapioOutputDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        String tipoVenda,
       // RestauranteOutputDTO restauranteOutputDTO,
        String pathFoto
) {

}
