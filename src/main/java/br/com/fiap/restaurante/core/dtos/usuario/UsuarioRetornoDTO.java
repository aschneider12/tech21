package br.com.fiap.restaurante.core.dtos.usuario;

import br.com.fiap.restaurante.core.domain.entities.Usuario;

import java.time.LocalDateTime;

public record UsuarioRetornoDTO(

        String nome,

        String email,

        String login,
        String senha
//        List<TipoUsuario> perfis,
//        Endereco endereco
) {

    public static UsuarioRetornoDTO fromEntity(Usuario ent) {

        return new UsuarioRetornoDTO(ent.getNome(), ent.getEmail(), ent.getLogin(),
                ent.getSenha()
        );
    }
}
