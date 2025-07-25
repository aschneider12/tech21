package br.com.fiap.restaurante.infra.database.mappers;

import br.com.fiap.restaurante.core.domain.models.Restaurante;
import br.com.fiap.restaurante.infra.database.entities.RestauranteEntity;
import br.com.fiap.restaurante.infra.dtos.RestauranteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteEntityMapper {

    RestauranteEntityMapper INSTANCE = Mappers.getMapper(RestauranteEntityMapper.class);

    RestauranteEntity toEntity(Restaurante restauranteFromDomain);

    List<RestauranteEntity> toEntity(List<Restaurante> restaurantesFromDomain);

    Restaurante toDomain(RestauranteEntity restauranteEntity);

    List<Restaurante> toDomain(List<RestauranteEntity> restaurantesEntities);
}
