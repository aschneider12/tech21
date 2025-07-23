package br.com.fiap.restaurante.core.dtos.restaurante;

public record RestauranteOutputDTO(
        Long id,
        String nome,
        String tipoCozinha,
        String horarioFuncionamento
){
}
