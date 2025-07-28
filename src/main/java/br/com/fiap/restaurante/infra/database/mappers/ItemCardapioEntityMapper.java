package br.com.fiap.restaurante.infra.database.mappers;

import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.infra.database.entities.ItemCardapioEntity;
import br.com.fiap.restaurante.infra.database.entities.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemCardapioEntityMapper {

    ItemCardapioEntityMapper INSTANCE = Mappers.getMapper(ItemCardapioEntityMapper.class);

    @Mapping(target = "restaurante.dono", ignore = true)
    ItemCardapioEntity toEntity(ItemCardapio itemFromDomain);

//    List<ItemCardapioEntity> toEntity(List<ItemCardapio> itensFromDomain);

    @Mapping(target = "restaurante", source = "restaurante", qualifiedByName = "mapRestauranteSemItens")
    ItemCardapio toDomain(ItemCardapioEntity itemCardapioEntity);

    @Mapping(target = "restaurante", source = "restaurante", qualifiedByName = "mapRestauranteSemItens")
    List<ItemCardapio> toDomain(List<ItemCardapioEntity> itensCardapioEntity);

    @Named("mapRestauranteSemItens")
    default Restaurante mapRestauranteSemItens(RestauranteEntity entity) {

        if (entity == null) return null;

        Restaurante novo = new Restaurante();
        novo.setId(entity.getId());
        novo.setNome(entity.getNome());

        return novo;
    }
}
