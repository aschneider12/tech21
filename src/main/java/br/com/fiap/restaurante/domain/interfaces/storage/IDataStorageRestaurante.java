package br.com.fiap.restaurante.domain.interfaces.storage;


import br.com.fiap.restaurante.domain.models.Restaurante;

import java.util.List;

public interface IDataStorageRestaurante {

    Restaurante cadastrar(Restaurante domain);
    Restaurante atualizar(Restaurante dto);
    boolean deletar(Long id);

    List<Restaurante> buscarTodosRestaurantes();
    Restaurante buscarRestaurantePorIdentificador(Long id);
    Restaurante buscarRestaurantePorNome(String nome);

}
