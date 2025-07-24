package br.com.fiap.restaurante.core.mappers;

import br.com.fiap.restaurante.core.domain.entities.Usuario;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioInputDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioOutputDTO;

public class UsuarioMapper {

    public static UsuarioOutputDTO toOutput(Usuario usuario){

        if(usuario == null) return null;

        return UsuarioOutputDTO.create(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getDataUltimaAlteracao(),
                usuario.getPerfis(),
                usuario.getEndereco() != null ? EnderecoMapper.toDTO(usuario.getEndereco()) : null
        );
    }

    public static UsuarioInputDTO toInput(Usuario usuario){

        if(usuario == null) return null;

        return UsuarioInputDTO.create(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getPerfis(),
                EnderecoMapper.toDTO(usuario.getEndereco())
        );
    }

    public static Usuario toDomain(UsuarioOutputDTO dto) {

        return Usuario.fromDTO(dto);
    }
}
