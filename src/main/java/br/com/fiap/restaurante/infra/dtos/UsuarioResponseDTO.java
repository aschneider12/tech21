package br.com.fiap.restaurante.infra.dtos;

import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;

import java.util.List;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        List<TipoUsuario> perfis,
        EnderecoDTO endereco
) {
}
