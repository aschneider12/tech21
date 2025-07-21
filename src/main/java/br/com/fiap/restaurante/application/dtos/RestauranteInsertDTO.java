package br.com.fiap.restaurante.application.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestauranteInsertDTO(


        @NotBlank(message = "Nome do restaurante não pode estar em branco")
        String nome,

        @NotBlank(message = "Tipo de Cozinha não pode estar em branco")
        String tipoCozinha,

        @NotBlank(message = "Horario de Funcionamento não pode estar em branco")
        String horarioFuncionamento,

        @NotNull(message = "O ID do dono não pode ser nulo.")
        Long dono,

        //@Valid
        EnderecoRequestDTO endereco


) {
}
