package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.entities.Restaurante;

public record RestauranteResponseDTO(

        Long id,
        String nome,
        String tipoCozinha,
        String horarioFuncionamento


) {


    public RestauranteResponseDTO(Restaurante restaurante) {

        this (
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento()
        );

    }
}
