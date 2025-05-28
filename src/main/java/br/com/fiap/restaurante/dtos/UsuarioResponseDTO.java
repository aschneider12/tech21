package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.entities.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        @Schema(description = "Data da última alteração realizada no cadastro.")
        LocalDate dataUltimaAlteracao,
        TipoUsuario tipoUsuario
) {}
