package br.com.fiap.restaurante.core.interfaces;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;

import java.util.List;

public interface IRestauranteGateway {

    Restaurante cadastrar(Restaurante restaurante);
    List<Restaurante> buscarTodosRestaurantes();

    Restaurante buscarRestaurantePorIdentificador(Long id);
    Restaurante buscarRestaurantePorNome(String nomeRestaurante);
}
