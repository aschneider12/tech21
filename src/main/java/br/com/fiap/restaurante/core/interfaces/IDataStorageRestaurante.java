package br.com.fiap.restaurante.core.interfaces;


import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteCadastroDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteRetornoDTO;

import java.util.List;

public interface IDataStorageRestaurante {

    RestauranteRetornoDTO cadastrar(RestauranteCadastroDTO dto);
    List<RestauranteRetornoDTO> buscarTodosRestaurantes();
    RestauranteRetornoDTO buscarRestaurantePorIdentificador(Long id);
    RestauranteRetornoDTO buscarRestaurantePorNome(String nome);


}
