package br.com.fiap.restaurante.infra.dtos;

import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;

import java.util.List;
import java.util.Set;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        Set<TipoUsuario> perfis,
        EnderecoDTO endereco
) {
}
