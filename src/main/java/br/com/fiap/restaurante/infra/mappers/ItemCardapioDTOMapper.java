package br.com.fiap.restaurante.infra.mappers;

import br.com.fiap.restaurante.application.input.RestauranteInput;
import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.infra.dtos.ItemResponseDTO;
import br.com.fiap.restaurante.infra.dtos.RestauranteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemCardapioDTOMapper {

    ItemCardapioDTOMapper INSTANCE = Mappers.getMapper(ItemCardapioDTOMapper.class);

//    @Mapping(target = "dataUltimaAlteracao", ignore = true)
//    @Mapping(target = "dono", source = "dono", qualifiedByName = "convertUsuarioToDono")
    ItemResponseDTO toDTO(ItemCardapioOutput itemFromDomain);

    List<ItemResponseDTO> toDTO(List<ItemCardapioOutput> itensFromDomain);

//    @Mapping(target = "itensCardapio", ignore = true)
//    @Mapping(target = "dono", source = "dono", qualifiedByName = "convertDonoToUsuario")
    RestauranteInput toInputApplication(RestauranteDTO restauranteDTO);

    @Named("convertDonoToUsuario")
    default UsuarioOutput mapLongToUsuario(Long dono) {
        return UsuarioOutput.create(dono, null,null, null,null,null,null);
    }

    @Named("convertUsuarioToDono")
    default Long mapUsuarioToLong(UsuarioOutput dono) {
        return dono.id();
    }


}
