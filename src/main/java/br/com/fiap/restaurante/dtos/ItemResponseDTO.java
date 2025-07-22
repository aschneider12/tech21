package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.entities.TipoVenda;

import java.math.BigDecimal;

public record ItemResponseDTO(
    Long id,
    String nome,
    String descricao,
    BigDecimal preco,
    TipoVenda tipoVenda,
    Long restauranteId,
    String pathFoto
) {}
