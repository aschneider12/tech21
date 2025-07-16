package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.entities.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * No update o usuário só poderá atualizar alguns campos,
 * outros são tratados por controllers especificos, como senha e perfis
 *
 * @param nome
 * @param email
 * @param login
 * @param endereco
 */
public record UsuarioUpdateDTO(

    @NotBlank(message = "Nome não pode ser vazio.")
    String nome,

    @Email(message = "E-mail inválido.")
    @Schema(description = "E-mail válido do usuário", example = "email@email.com")
    String email,

    String login,

    @Schema
    Endereco endereco
) { }


