package br.com.fiap.restaurante.core.presenters;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteRetornoDTO;

public class RestaurantePresenter {

    //USADO EM CASOS COMO O CPF, MASCARAR O CPF ANTES DE RETORNAR PARA A APPLICATION
    public static RestauranteRetornoDTO ToDTO(Restaurante restaurante) {

        //  final String identificacao = restaurante.getIdentificacaoInterna();
        //  final String identificacaoOfuscada = identificacao.charAt(1) + "..." + identificacao.charAt(identificacao.length() - 1);

        return new RestauranteRetornoDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento()
        );
    }

}
