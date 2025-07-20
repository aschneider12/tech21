package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.entities.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep
) {
    public EnderecoResponseDTO (Endereco endereco){

        this(
                endereco.getId(),
                endereco.getRua(),
                endereco.getCep(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getNumero()
        );
    }

}