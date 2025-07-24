package br.com.fiap.restaurante.core.mappers;

import br.com.fiap.restaurante.core.domain.entities.Endereco;
import br.com.fiap.restaurante.core.dtos.endereco.EnderecoDTO;

public class EnderecoMapper {

    public static EnderecoDTO toDTO(Endereco endereco){

        if(endereco == null)
            return null;

        return EnderecoDTO.create(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }

    public static Endereco toDomain(EnderecoDTO dto) {

        return new Endereco(dto.id(), dto.rua(),
                dto.numero(), dto.cidade(), dto.estado(), dto.cep());

    }
}
