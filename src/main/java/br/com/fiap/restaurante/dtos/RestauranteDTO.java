package br.com.fiap.restaurante.dtos;

import jakarta.validation.constraints.NotBlank;

public record RestauranteDTO(

        Long id,

        @NotBlank(message = "Nome do restaurante não pode estar em branco")
        String nome) {
}
