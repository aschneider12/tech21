package br.com.fiap.restaurante.core.interfaces.storage;


import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;

import java.util.List;

public interface IDataStorageRestaurante {

    RestauranteOutputDTO cadastrar(RestauranteInputDTO dto);
    RestauranteOutputDTO atualizar(RestauranteInputDTO dto);
    boolean deletar(Long id);

    List<RestauranteOutputDTO> buscarTodosRestaurantes();
    RestauranteOutputDTO buscarRestaurantePorIdentificador(Long id);
    RestauranteOutputDTO buscarRestaurantePorNome(String nome);


}
