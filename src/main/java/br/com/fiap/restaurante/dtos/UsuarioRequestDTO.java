package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.entities.TipoUsuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record UsuarioRequestDTO(

        @NotBlank(message = "Nome não pode ser vazio.")
        String nome,

        @Email(message = "E-mail inválido.")
        @Schema(description = "E-mail válido do usuário", example = "email@email.com")
        String email,

        @NotBlank
        String login,

        @NotBlank
        String senha,

        TipoUsuario tipoUsuario
) {



}

