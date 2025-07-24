package br.com.fiap.restaurante.core.dtos.usuario;

import br.com.fiap.restaurante.core.domain.entities.Endereco;
import br.com.fiap.restaurante.core.domain.entities.Usuario;
import br.com.fiap.restaurante.core.domain.entities.UsuarioPerfil;
import br.com.fiap.restaurante.core.dtos.endereco.EnderecoDTO;

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
