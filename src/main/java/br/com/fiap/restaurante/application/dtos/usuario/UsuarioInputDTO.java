package br.com.fiap.restaurante.application.dtos.usuario;

import br.com.fiap.restaurante.infra.dtos.EnderecoDTO;

import java.util.List;

public record UsuarioInputDTO(

        String nome,

        String email,

        String login,
        String senha,
        List<String> perfis,
        EnderecoDTO endereco
) {
    public static UsuarioInputDTO create(
                                           String nome,
                                           String email,
                                           String login,
                                           String senha,
                                           List<String> perfis,
                                           EnderecoDTO endereco){

        return new UsuarioInputDTO(nome,email,login,senha,perfis,endereco);
    }
}
