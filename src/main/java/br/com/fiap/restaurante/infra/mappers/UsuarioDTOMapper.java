package br.com.fiap.restaurante.infra.mappers;

import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
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
    UsuarioDTO toDTO(UsuarioOutput usuario);


    @Named(value = "mapUsuarioPerfilToStrings")
    default List<String> mapUsuarioPerfil(List<UsuarioPerfil> perfis) {
        if (perfis == null) return null;
        return perfis.stream()
                .map(u -> u.getTipoUsuario().name()) // extrai o nome do perfil
                .toList();
    }

    @Named(value = "mapStringsToUsuarioPerfil")
    default List<UsuarioPerfil> mapStrings(List<String> perfis) {
        if (perfis == null) return null;
        return perfis.stream()
                .map(p -> {
                    UsuarioPerfil up = new UsuarioPerfil();
                    up.setTipoUsuario(TipoUsuario.valueOf(p));
                    return up;
                })
                .toList();
    }
}

