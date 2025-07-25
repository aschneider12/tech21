package br.com.fiap.restaurante.infra.dtos;

public record EnderecoRequestDTO(
        Long id,
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep
) {}

