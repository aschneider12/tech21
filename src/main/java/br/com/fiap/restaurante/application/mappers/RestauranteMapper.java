package br.com.fiap.restaurante.application.mappers;

import br.com.fiap.restaurante.application.dtos.RestauranteInsertDTO;
import br.com.fiap.restaurante.application.dtos.RestauranteResponseDTO;
import br.com.fiap.restaurante.application.dtos.RestauranteUpdateDTO;
import br.com.fiap.restaurante.application.entities.RestauranteEntity;

import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    //RestauranteMapper INSTANCE = Mappers.getMapper(RestauranteMapper.class);

    RestauranteResponseDTO toClient(RestauranteOutputDTO dtoFromDomain);
    List<RestauranteResponseDTO> toClientList(List<RestauranteOutputDTO> dtosFromDomain);

    RestauranteInputDTO toInputDomain(RestauranteInsertDTO insertDTO);
    RestauranteInputDTO toInputDomain(RestauranteUpdateDTO updateDTO);

    RestauranteOutputDTO toOutputDomain(RestauranteEntity entityFromDataBase);

    @Mapping(target = "dono", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "itemsCardapio", ignore = true)
    RestauranteEntity toEntity(RestauranteInsertDTO dto);


    @Mapping(target = "dono", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "itemsCardapio", ignore = true)
    RestauranteEntity toEntity(RestauranteUpdateDTO dto);
}
