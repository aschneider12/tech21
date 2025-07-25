package br.com.fiap.restaurante.refactor.infraestrutura.database.mapper;

import br.com.fiap.restaurante.refactor.domain.model.RestauranteDomain;
import br.com.fiap.restaurante.refactor.infraestrutura.database.entities.TesteRestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RestauranteEntityMapper {

    RestauranteEntityMapper INSTANCE = Mappers.getMapper(RestauranteEntityMapper.class);

    TesteRestauranteEntity toTesteRestauranteEntity(RestauranteDomain restauranteDomain);
}
