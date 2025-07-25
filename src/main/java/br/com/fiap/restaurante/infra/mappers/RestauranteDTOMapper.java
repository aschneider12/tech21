package br.com.fiap.restaurante.infra.mappers;

import br.com.fiap.restaurante.core.domain.models.Restaurante;
import br.com.fiap.restaurante.core.domain.models.Usuario;
import br.com.fiap.restaurante.infra.dtos.RestauranteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteDTOMapper {

    RestauranteDTOMapper INSTANCE = Mappers.getMapper(RestauranteDTOMapper.class);

    @Mapping(target = "dono", source = "dono", qualifiedByName = "convertUsuarioToDono")
    RestauranteDTO toDTO(Restaurante restauranteFromDomain);

    List<RestauranteDTO> toDTO(List<Restaurante> restaurantesFromDomain);

    @Mapping(target = "itensCardapio", ignore = true)
    @Mapping(target = "dono", source = "dono", qualifiedByName = "convertDonoToUsuario")
    Restaurante toDomain(RestauranteDTO restauranteDTO);

    List<Restaurante> toDomain(List<RestauranteDTO> restaurantesDTOs);

    @Named("convertDonoToUsuario")
    default Usuario customConvertDonoToUsuario(Long dono) {
        return new Usuario(dono);
    }
    @Named("convertUsuarioToDono")
    default Long customConvertDonoToUsuario(Usuario dono) {
        return dono.getId();
    }
}
