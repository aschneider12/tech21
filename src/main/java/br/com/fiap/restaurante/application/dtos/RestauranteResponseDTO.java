package br.com.fiap.restaurante.application.dtos;


public record RestauranteResponseDTO(

        Long id,
        String nome,
        String tipoCozinha,
        String horarioFuncionamento,
        UsuarioResponseDTO dono,
        EnderecoResponseDTO endereco
) {}