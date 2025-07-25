package br.com.fiap.restaurante.refactor.infraestrutura.controller.mapper;

import br.com.fiap.restaurante.gen.model.RestauranteDto;
import br.com.fiap.restaurante.refactor.domain.model.RestauranteDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RestauranteDtoMapper {

    RestauranteDtoMapper INSTANCE = Mappers.getMapper(RestauranteDtoMapper.class);

    @Mapping(target = "id", ignore = true)
    RestauranteDomain toRestauranteDomain(RestauranteDto restauranteDto);
}
