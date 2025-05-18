package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.entities.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record UsuarioRequestDTO(
        @NotBlank(message = "Nome não pode ser vazio.")
        String nome,
        @Email(message = "E-mail inválido.")
        String email,
        @NotBlank
        String login,
        @NotBlank
        String senha,
        TipoUsuario tipoUsuario
) {



}

