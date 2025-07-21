package br.com.fiap.restaurante.application.dtos;

import jakarta.validation.constraints.NotBlank;

public record RestauranteUpdateDTO(

        Long id,

        @NotBlank(message = "Nome do restaurante não pode estar em branco")
        String nome,

        String tipoCozinha,

        String horarioFuncionamento

)

{
}
