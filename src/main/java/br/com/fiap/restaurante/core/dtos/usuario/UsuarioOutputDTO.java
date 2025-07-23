package br.com.fiap.restaurante.core.dtos.usuario;

import br.com.fiap.restaurante.core.domain.entities.Usuario;

public record UsuarioOutputDTO (

        String nome,

        String email,

        String login,
        String senha
//        List<TipoUsuario> perfis,
//        Endereco endereco
) {

    public static UsuarioOutputDTO fromEntity(Usuario ent) {

        return new UsuarioOutputDTO(ent.getNome(), ent.getEmail(), ent.getLogin(),
                ent.getSenha()
        );
    }
}
