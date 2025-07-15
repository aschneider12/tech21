package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.entities.TipoUsuario;
import br.com.fiap.restaurante.entities.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        @Schema(description = "Data da última alteração realizada no cadastro.")
        LocalDate dataUltimaAlteracao
        //TipoUsuario tipoUsuario
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getLogin(),
            usuario.getDataUltimaAlteracao()
            //usuario.getTipoUsuario()
        );
    }
}
