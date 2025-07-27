package br.com.fiap.restaurante.infra.database.mappers;

import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.infra.database.entities.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteEntityMapper {

    RestauranteEntityMapper INSTANCE = Mappers.getMapper(RestauranteEntityMapper.class);

    @Mapping(target = "dono.perfis", source = "dono.perfis", ignore = true)
    @Mapping(target = "dono", source = "dono", ignore = true)
    RestauranteEntity toEntity(Restaurante restauranteFromDomain);

    @Mapping(target = "dono.perfis", ignore = true)
    @Mapping(target = "dono", source = "dono", ignore = true)
    List<RestauranteEntity> toEntity(List<Restaurante> restaurantesFromDomain);

    @Mapping(target = "dono", source = "dono", ignore = true)
    @Mapping(target = "dono.perfis", source = "dono.perfis", ignore = true)
    @Mapping(target = "itensCardapio", source = "itensCardapio", ignore = true)
    Restaurante toDomain(RestauranteEntity restauranteEntity);

    @Mapping(target = "dono", source = "dono", ignore = true)
    @Mapping(target = "dono.perfis", source = "dono.perfis", ignore = true)
    @Mapping(target = "itensCardapio", source = "itensCardapio", ignore = true)
    List<Restaurante> toDomain(List<RestauranteEntity> restaurantesEntities);
}
