package br.com.fiap.restaurante.core.dtos.itemcardapio;

import java.math.BigDecimal;

public record ItemCardapioOutputDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        String tipoVenda,
        String pathFoto
) {}