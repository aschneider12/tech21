package br.com.fiap.restaurante.core.dtos.usuario;

import br.com.fiap.restaurante.core.domain.entities.TipoUsuarioEnum;
import br.com.fiap.restaurante.core.domain.entities.Usuario;
import br.com.fiap.restaurante.core.dtos.endereco.EnderecoDTO;

import java.time.LocalDateTime;
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
