package br.com.fiap.restaurante.core.mappers;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;

import java.util.List;

public class RestauranteMapper {

    public static RestauranteInputDTO toInput(Restaurante restaurante){

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

    public static Restaurante toDomain(RestauranteOutputDTO dto){

        if(dto == null) return null;

        return Restaurante.create(
                dto.id(),
                dto.nome(),
                dto.tipoCozinha(),
                dto.horarioFuncionamento(),
                EnderecoMapper.toDomain(dto.endereco()),
                UsuarioMapper.toDomain(dto.dono()),
                ItemCardapioMapper.toDomain(dto.itensCardapio())
        );
    }
    public static List<Restaurante> toDomain(List<RestauranteOutputDTO> dtos){

        if(dtos == null || dtos.isEmpty()) return null;

        return dtos.stream().map(d ->
         Restaurante.create(
                d.id(),
                d.nome(),
                d.tipoCozinha(),
                d.horarioFuncionamento(),
                EnderecoMapper.toDomain(d.endereco()),
                UsuarioMapper.toDomain(d.dono()),
                ItemCardapioMapper.toDomain(d.itensCardapio())
        )).toList();
    }

    public static Restaurante toDomain(RestauranteInputDTO dto){

        if(dto == null) return null;

        return Restaurante.create(
                dto.id(),
                dto.nome(),
                dto.tipoCozinha(),
                dto.horarioFuncionamento(),
                EnderecoMapper.toDomain(dto.endereco()),
                UsuarioMapper.toDomain(dto.dono()),
                ItemCardapioMapper.toDomain(dto.itensCardapio())
        );
    }

    public static Restaurante toDomainWithoutRelationship(RestauranteOutputDTO dto){

        if(dto == null) return null;
        return new Restaurante(dto.id());
    }
}
