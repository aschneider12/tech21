package br.com.fiap.restaurante.infra.database.mappers;

import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.entities.UsuarioPerfil;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    UsuarioEntityMapper INSTANCE = Mappers.getMapper(UsuarioEntityMapper.class);

    @Mapping(target = "perfis", source = "perfis")
    @IterableMapping(qualifiedByName = "mapStringsToUsuarioPerfil")
    Usuario toDomain(UsuarioEntity entity);

    @Mapping(target = "perfis", source = "perfis")
    @IterableMapping(qualifiedByName = "mapUsuarioPerfilToStrings")
    UsuarioEntity toEntity(Usuario domain);

    @Named("mapStringsToUsuarioPerfil")
    default List<String> mapStrings(List<UsuarioPerfil> perfis) {
        if (perfis == null) return null;
        return perfis.stream()
                .map(u -> u.getTipoUsuario().name())
                .collect(Collectors.toList());
    }

    @Named("mapUsuarioPerfilToStrings")
    default List<UsuarioPerfil> mapPerfis(List<String> perfis) {
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


