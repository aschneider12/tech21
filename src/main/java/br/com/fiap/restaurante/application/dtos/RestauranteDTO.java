package br.com.fiap.restaurante.application.dtos;

import jakarta.validation.constraints.NotBlank;

@Deprecated
public record RestauranteDTO(

        Long id,

        @NotBlank(message = "Nome do restaurante não pode estar em branco")
        String nome) {
}
