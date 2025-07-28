package br.com.fiap.restaurante.infra.database.mappers;

import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.entities.UsuarioPerfilEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    UsuarioEntityMapper INSTANCE = Mappers.getMapper(UsuarioEntityMapper.class);

    @Mapping(target = "perfis", source = "perfis", qualifiedByName = "mapUsuarioPerfilToStrings")
    Usuario toDomain(UsuarioEntity entity);

    List<Usuario> toDomain(List<UsuarioEntity> entities);

    @Mapping(target = "perfis", source = "perfis", qualifiedByName = "mapStringsToUsuarioPerfil")
    UsuarioEntity toEntity(Usuario domain, @Context Long idUsuario);

    default UsuarioEntity toEntity(Usuario domain){

        return toEntity(domain, domain.getId());
    }

    @Named("mapUsuarioPerfilToStrings")
    default Set<String> mapStrings(Set<UsuarioPerfilEntity> usuarioPerfis) {
        if (usuarioPerfis == null) return null;
        return usuarioPerfis.stream()
                .map(u -> u.getTipoUsuario().name())
                .collect(Collectors.toSet());
    }

    @Named("mapStringsToUsuarioPerfil")
    default Set<UsuarioPerfilEntity> mapPerfis(Set<String> stringPerfis, @Context Long contextId) {
        if (stringPerfis == null) return null;
        return stringPerfis.stream()
                .map(p -> {
                    UsuarioPerfilEntity up = new UsuarioPerfilEntity();
                    up.setUsuarioId(contextId);
                    up.setTipoUsuario(TipoUsuario.valueOf(p));
                    return up;
                })
                .collect(Collectors.toSet());
    }

}



