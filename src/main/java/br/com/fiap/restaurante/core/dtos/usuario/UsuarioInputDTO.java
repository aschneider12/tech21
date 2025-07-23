package br.com.fiap.restaurante.core.dtos.usuario;

import br.com.fiap.restaurante.core.domain.entities.TipoUsuarioEnum;
import br.com.fiap.restaurante.core.domain.entities.Usuario;
import br.com.fiap.restaurante.core.dtos.endereco.EnderecoDTO;

import java.util.List;

public record UsuarioInputDTO(

        String nome,

        String email,

        String login,
        String senha,
        List<TipoUsuarioEnum> perfis,
        EnderecoDTO endereco
) {

    public static UsuarioInputDTO fromEntity(Usuario usuario) {
        return new UsuarioInputDTO(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha()
        );
    }
}
