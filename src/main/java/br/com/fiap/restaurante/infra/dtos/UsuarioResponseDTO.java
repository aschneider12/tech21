package br.com.fiap.restaurante.infra.dtos;

import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
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
    public UsuarioResponseDTO(UsuarioEntity usuarioEntity) {
        this(
            usuarioEntity.getId(),
            usuarioEntity.getNome(),
            usuarioEntity.getEmail(),
            usuarioEntity.getLogin(),
            usuarioEntity.getDataUltimaAlteracao()
            //usuario.getTipoUsuario()
        );
    }
}
