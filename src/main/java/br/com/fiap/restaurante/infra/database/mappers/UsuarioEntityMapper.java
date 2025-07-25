package br.com.fiap.restaurante.infra.database.mappers;

import br.com.fiap.restaurante.core.domain.models.Usuario;
import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.entities.UsuarioPerfil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    UsuarioEntityMapper INSTANCE = Mappers.getMapper(UsuarioEntityMapper.class);

    @Mapping(source = "perfis", target = "perfis", qualifiedByName = "mapStringsToUsuarioPerfil")
    Usuario toDomain(UsuarioEntity usuarioEntity);

    @Mapping(source = "perfis", target = "perfis", qualifiedByName = "mapUsuarioPerfilToStrings")
    UsuarioEntity toEntity(Usuario usuarioFromDomain);


    @Named("mapStringsToUsuarioPerfil")
    default List<String> map(List<UsuarioPerfil> perfis) {
        if (perfis == null) return null;
        return perfis.stream()
                .map(u -> u.getTipoUsuario().name()) // extrai o nome do perfil
                .collect(Collectors.toList());
    }

    @Named("mapUsuarioPerfilToStrings")
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

