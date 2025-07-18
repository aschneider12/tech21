package br.com.fiap.restaurante.dtos;

public record EnderecoResponseDTO(
        Long id,
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep
) {}