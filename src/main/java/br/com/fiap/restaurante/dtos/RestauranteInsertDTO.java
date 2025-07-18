package br.com.fiap.restaurante.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record RestauranteInsertDTO(


        @NotBlank(message = "Nome do restaurante não pode estar em branco")
        String nome,

        @NotBlank(message = "Tipo de Cozinha não pode estar em branco")
        String tipoCozinha,

        @NotBlank(message = "Horario de Funcionamento não pode estar em branco")
        String horarioFuncionamento,

        @NotBlank(message = "ID do Usuario nao pode ser nulo")
        Long dono,

        //@Valid
        EnderecoRequestDTO endereco


) {
}
