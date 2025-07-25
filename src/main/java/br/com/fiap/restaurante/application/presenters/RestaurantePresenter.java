package br.com.fiap.restaurante.application.presenters;

import br.com.fiap.restaurante.domain.domain.models.Restaurante;
import br.com.fiap.restaurante.application.dtos.restaurante.RestauranteOutputDTO;

public class RestaurantePresenter {

    //USADO EM CASOS COMO O CPF, MASCARAR O CPF ANTES DE RETORNAR PARA A APPLICATION
    public static RestauranteOutputDTO ToDTO(Restaurante restaurante) {

        //  final String identificacao = restaurante.getIdentificacaoInterna();
        //  final String identificacaoOfuscada = identificacao.charAt(1) + "..." + identificacao.charAt(identificacao.length() - 1);

//        return new RestauranteOutputDTO(
//                restaurante.getId(),
//                restaurante.getNome(),
//                restaurante.getTipoCozinha(),
//                restaurante.getHorarioFuncionamento()
//        );
        return null;
    }

}
