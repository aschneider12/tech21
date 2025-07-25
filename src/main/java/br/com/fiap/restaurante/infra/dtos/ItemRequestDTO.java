package br.com.fiap.restaurante.infra.dtos;

import br.com.fiap.restaurante.infra.database.entities.TipoVenda;

import java.math.BigDecimal;

public record ItemRequestDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        TipoVenda tipoVenda,
        Long restauranteId,
        String pathFoto
) {}
