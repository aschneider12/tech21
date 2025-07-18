package br.com.fiap.restaurante.dtos;

public record RestauranteResponseDTO(

        Long id,
        String nome,
        String tipoCozinha,
        String horarioFuncionamento,
        EnderecoResponseDTO dono,
        Long endereco


) {

}
