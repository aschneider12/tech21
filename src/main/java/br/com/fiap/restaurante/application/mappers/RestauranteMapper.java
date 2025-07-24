package br.com.fiap.restaurante.application.mappers;

import br.com.fiap.restaurante.application.dtos.RestauranteRequestDTO;
import br.com.fiap.restaurante.application.dtos.RestauranteResponseDTO;
import br.com.fiap.restaurante.application.entities.RestauranteEntity;
import br.com.fiap.restaurante.application.entities.UsuarioPerfil;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    //RestauranteMapper INSTANCE = Mappers.getMapper(RestauranteMapper.class);

    RestauranteResponseDTO toClient(RestauranteOutputDTO dtoFromDomain);
    List<RestauranteResponseDTO> toClientList(List<RestauranteOutputDTO> dtosFromDomain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dono", source = "dono", qualifiedByName = "convertDonoToUsuarioDTO")
    RestauranteInputDTO toInputDomain(RestauranteRequestDTO insertDTO);

    @Mapping(target = "dono.perfis", ignore = true)
    RestauranteOutputDTO toOutputDomain(RestauranteEntity entityFromDataBase);

    @Mapping(target = "dono.perfis", ignore = true)
    RestauranteEntity toEntity(RestauranteInputDTO dto);

    List<RestauranteOutputDTO> toOutputDomain(List<RestauranteEntity> entityFromDataBase);

    @Named("convertDonoToUsuarioDTO")
    default UsuarioOutputDTO customConvertDonoToUser(Long dono) {

        return UsuarioOutputDTO.create(dono, null, null, null, null, null, null, null);

    }
}
