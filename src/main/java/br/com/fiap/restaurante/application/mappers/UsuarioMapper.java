package br.com.fiap.restaurante.application.mappers;

import br.com.fiap.restaurante.application.entities.TipoUsuario;
import br.com.fiap.restaurante.application.entities.Usuario;
import br.com.fiap.restaurante.application.entities.UsuarioPerfil;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "perfis", target = "perfis", qualifiedByName = "mapStringsToUsuarioPerfil")
    UsuarioOutputDTO toDTO(Usuario usuario);

    @Mapping(source = "perfis", target = "perfis", qualifiedByName = "mapUsuarioPerfilToStrings")
    Usuario toEntity(UsuarioOutputDTO dto);


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

