package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.application.entities.TipoVenda;

import java.math.BigDecimal;

public record ItemRequestDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        TipoVenda tipoVenda,
        Long restauranteId,
        String pathFoto
) {}
