package br.com.fiap.restaurante.core.mappers;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;

import java.util.List;

public class RestauranteMapper {

    public static RestauranteInputDTO toOutput(Restaurante restaurante){

        if(restaurante == null) return null;

        return RestauranteInputDTO.create(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                EnderecoMapper.toDTO(restaurante.getEndereco()),
                UsuarioMapper.toOutput(restaurante.getDono()),
                ItemCardapioMapper.toOutput(restaurante.getItensCardapio())
        );
    }

    public static RestauranteOutputDTO toOutput(Restaurante restaurante){

        if(restaurante == null) return null;

        return RestauranteOutputDTO.create(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                EnderecoMapper.toDTO(restaurante.getEndereco()),
                UsuarioMapper.toOutput(restaurante.getDono()),
                ItemCardapioMapper.toOutput(restaurante.getItensCardapio())
        );
    }

    public static RestauranteOutputDTO toOutputWithoutRelationship(Restaurante restaurante){

        if(restaurante == null) return null;

        return RestauranteOutputDTO.create(
                restaurante.getId(),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static List<RestauranteOutputDTO> toOutput(List<Restaurante> restaurantes) {

        return restaurantes.stream().map(RestauranteMapper::toOutput).toList();
    }
}
