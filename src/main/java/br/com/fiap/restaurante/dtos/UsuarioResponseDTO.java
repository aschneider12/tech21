package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.entities.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        LocalDate dataUltimaAlteracao,
        TipoUsuario tipoUsuario
) {}
