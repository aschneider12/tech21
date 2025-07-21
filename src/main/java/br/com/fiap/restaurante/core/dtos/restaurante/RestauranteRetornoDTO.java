package br.com.fiap.restaurante.core.dtos.restaurante;

public record RestauranteRetornoDTO(
        Long id,
        String nome,
        String tipoCozinha,
        String horarioFuncionamento
){
}
