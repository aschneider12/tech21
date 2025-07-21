package br.com.fiap.restaurante.application.dtos;

import br.com.fiap.restaurante.application.entities.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        @Schema(description = "Data da última alteração realizada no cadastro.")
        LocalDateTime dataUltimaAlteracao
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
