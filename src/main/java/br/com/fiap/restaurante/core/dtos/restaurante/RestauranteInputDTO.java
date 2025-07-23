package br.com.fiap.restaurante.core.dtos.restaurante;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;

public record RestauranteInputDTO(
        String nome,
        String tipoCozinha,
        String horarioFuncionamento) {

    public static RestauranteInputDTO fromEntity(Restaurante restaurante) {
        return new RestauranteInputDTO(
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento()
        );
    }
}

