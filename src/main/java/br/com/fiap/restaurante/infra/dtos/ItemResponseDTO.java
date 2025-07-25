package br.com.fiap.restaurante.infra.dtos;

import br.com.fiap.restaurante.infra.database.entities.TipoVenda;

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
