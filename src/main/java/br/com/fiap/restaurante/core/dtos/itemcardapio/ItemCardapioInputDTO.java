package br.com.fiap.restaurante.core.dtos.itemcardapio;

import java.math.BigDecimal;

public record ItemCardapioInputDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        String tipoVenda,
        String pathFoto
) {}
