package br.com.fiap.restaurante.application.input;

import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.domain.models.Usuario;

public record RestauranteInput(
    String nome,
    String tipoCozinha,
    String horarioFuncionamento,
    EnderecoInput endereco,
    Long dono
) {

    public static RestauranteInput create(
                                            String nome,
                                            String tipoCozinha,
                                            String horarioFuncionamento,
                                            EnderecoInput endereco,
                                            Long dono){
        return new RestauranteInput(nome, tipoCozinha, horarioFuncionamento, endereco, dono);
    }



    public static Restaurante toDomain(RestauranteInput input) {

        return new Restaurante(input.nome(), input.tipoCozinha(),
                input.horarioFuncionamento(),
                EnderecoInput.toDomain(input.endereco()),
                new Usuario(input.dono()));
    }
}
