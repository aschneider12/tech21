package br.com.fiap.restaurante.infra.mappers;

import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.entities.UsuarioPerfil;
import br.com.fiap.restaurante.infra.dtos.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {

    UsuarioDTOMapper INSTANCE = Mappers.getMapper(UsuarioDTOMapper.class);

    @Mapping(source = "perfis", target = "perfis", qualifiedByName = "mapStringsToUsuarioPerfil")
    UsuarioDTO toDTO(UsuarioEntity usuarioEntity);

    @Mapping(source = "perfis", target = "perfis", qualifiedByName = "mapUsuarioPerfilToStrings")
    UsuarioEntity toEntity(UsuarioDTO dto);



    @Named("mapUsuarioPerfilToStrings")
    default List<String> map(List<UsuarioPerfil> perfis) {
        if (perfis == null) return null;
        return perfis.stream()
                .map(u -> u.getTipoUsuario().name()) // extrai o nome do perfil
                .collect(Collectors.toList());
    }

    @Named("mapStringsToUsuarioPerfil")
    default List<UsuarioPerfil> mapStrings(List<String> perfis) {
        if (perfis == null) return null;
        return perfis.stream()
                .map(p -> {
                    UsuarioPerfil up = new UsuarioPerfil();
                    up.setTipoUsuario(TipoUsuario.valueOf(p));
                    return up;
                })
                .collect(Collectors.toList());
    }
}

