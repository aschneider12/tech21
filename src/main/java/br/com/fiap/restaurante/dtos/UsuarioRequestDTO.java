package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.entities.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioRequestDTO(

    @NotBlank(message = "Nome não pode ser vazio.")
    String nome,

    @Email(message = "E-mail inválido.")
    @Schema(description = "E-mail válido do usuário", example = "email@email.com")
    String email,

    @NotBlank(message = "Login não pode ser vazio.")
    String login,

    @NotBlank(message = "Senha não pode ser vazia.")
    @Schema(description = "Senha com no mínimo 8 caracteres, incluindo letras e números.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
             message = "Senha fraca. A senha deve ter pelo menos 8 caracteres e conter números.")
    String senha,

    @NotNull(message = "Tipo de usuário não pode ser nulo.")
    TipoUsuario tipoUsuario
) { }


