package br.com.fiap.restaurante.application.dtos.usuario;

import br.com.fiap.restaurante.infra.dtos.EnderecoDTO;

import java.time.LocalDateTime;
import java.util.List;

public record UsuarioOutputDTO (

        Long id,
        String nome,
        String email,
        String login,
        String senha,
         LocalDateTime dataUltimaAlteracao,
        List<String> perfis,
        EnderecoDTO endereco
) {

    public static UsuarioOutputDTO create( Long id,
                                           String nome,
                                           String email,
                                           String login,
                                           String senha,
                                           LocalDateTime dataUltimaAlteracao,
                                           List<String> perfis,
                                           EnderecoDTO endereco){

        return new UsuarioOutputDTO(id,nome,email,login,senha,dataUltimaAlteracao,perfis,endereco);
    }
}
