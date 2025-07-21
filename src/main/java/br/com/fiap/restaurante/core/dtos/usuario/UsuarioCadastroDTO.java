package br.com.fiap.restaurante.core.dtos.usuario;

import br.com.fiap.restaurante.core.domain.entities.Usuario;

public record UsuarioCadastroDTO(

        String nome,

        String email,

        String login,
        String senha
//        List<TipoUsuario> perfis,
//        Endereco endereco
) {

    public static UsuarioCadastroDTO fromEntity(Usuario usuario) {
        return new UsuarioCadastroDTO(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha()
        );
    }
}
