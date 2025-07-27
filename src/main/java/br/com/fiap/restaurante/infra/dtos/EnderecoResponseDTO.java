package br.com.fiap.restaurante.infra.dtos;

import br.com.fiap.restaurante.infra.database.entities.EnderecoEntity;

public record EnderecoResponseDTO(
        Long id,
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep
) {
    public EnderecoResponseDTO (EnderecoEntity endereco){

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