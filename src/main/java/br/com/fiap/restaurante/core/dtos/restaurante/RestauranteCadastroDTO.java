package br.com.fiap.restaurante.core.dtos.restaurante;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;

public record RestauranteCadastroDTO(
        String nome,
        String tipoCozinha,
        String horarioFuncionamento) {

    public static RestauranteCadastroDTO fromEntity(Restaurante restaurante) {
        return new RestauranteCadastroDTO(
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento()
        );
    }
}

