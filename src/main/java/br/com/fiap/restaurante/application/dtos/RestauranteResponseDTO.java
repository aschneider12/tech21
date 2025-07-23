package br.com.fiap.restaurante.application.dtos;


import br.com.fiap.restaurante.application.entities.RestauranteEntity;

public record RestauranteResponseDTO(

        Long id,
        String nome,
        String tipoCozinha,
        String horarioFuncionamento


) {

    public RestauranteResponseDTO {
    }

    public RestauranteResponseDTO(RestauranteEntity restaurante) {

        this (
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento()
        );

    }
}
