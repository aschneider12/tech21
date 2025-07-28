package br.com.fiap.restaurante.infra.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//Convertido via mapper
public record RestauranteDTO(

        Long id,

        @NotBlank(message = "Nome do restaurante não pode estar em branco")
        String nome,

        @NotBlank(message = "Tipo de Cozinha não pode estar em branco")
        String tipoCozinha,

        @NotBlank(message = "Horario de Funcionamento não pode estar em branco")
        String horarioFuncionamento,

        @NotNull(message = "O ID do dono (usuarioId) não pode ser nulo.")

        Long dono,

        @Valid
        EnderecoDTO endereco
) {}
