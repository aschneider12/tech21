package br.com.fiap.restaurante.infra.mappers;

import br.com.fiap.restaurante.application.input.UsuarioInput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
import br.com.fiap.restaurante.infra.dtos.UsuarioInsertDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {

    UsuarioDTOMapper INSTANCE = Mappers.getMapper(UsuarioDTOMapper.class);

//    @Mapping(source = "perfis", target = "perfis", qualifiedByName = "mapTipoUsuarioToStrings")
//    UsuarioDTO toDTO(UsuarioOutput usuario);

    @Mapping(source = "perfis", target = "perfis", qualifiedByName = "mapTipoUsuarioToStrings")
//    @Mapping(target = "senha", source = "senha", ignore = true)
    UsuarioResponseDTO toResponse(UsuarioOutput usuario);


    @Mapping(source = "perfis", target = "perfis", qualifiedByName = "mapTipoUsuarioToStrings")
//    @Mapping(target = "senha", source = "senha", ignore = true)
    List<UsuarioResponseDTO> toResponse(List<UsuarioOutput> usuarios);

    @Mapping(source = "perfis", target = "perfis", qualifiedByName = "mapStringToTipoUsuario")
    UsuarioInput toInputApplication(UsuarioInsertDTO usuario);

    @Mapping(target = "perfis", ignore = true)
    @Mapping(target = "senha", ignore = true)
    UsuarioInput toInputApplication(UsuarioUpdateDTO usuario);

    @Named("mapStringToTipoUsuario")
    default Set<String> mapTipoUsuarioToStrings(Set<TipoUsuario> perfis) {
        if (perfis == null) return null;
        return perfis.stream()
                .map(u -> u.name()) // extrai o nome do perfil
                .collect(Collectors.toSet());
    }

    @Named("mapTipoUsuarioToStrings")
    default Set<TipoUsuario> mapStringToTipoUsuario(Set<String> perfis) {
        if (perfis == null) return null;
        return perfis.stream()
                .map(TipoUsuario::valueOf) // extrai o nome do perfil
                .collect(Collectors.toSet());
    }

}

