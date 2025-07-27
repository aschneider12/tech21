package br.com.fiap.restaurante.application.input;

import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.infra.dtos.EnderecoDTO;

import java.util.List;

public record UsuarioInput (

        String nome,

        String email,

        String login,
        String senha,
        List<String> perfis,
        EnderecoInput endereco
) {
    public static UsuarioInput create(
                                           String nome,
                                           String email,
                                           String login,
                                           String senha,
                                           List<String> perfis,
                                           EnderecoInput endereco){

        return new UsuarioInput(nome,email,login,senha,perfis, endereco);
    }
    public static Usuario toDomain(UsuarioInput input) {

        return new Usuario(null, input.nome(), input.email(), input.login(),
                input.senha(),
                EnderecoInput.toDomain(input.endereco()),
                input.perfis()
                );
    }
}
