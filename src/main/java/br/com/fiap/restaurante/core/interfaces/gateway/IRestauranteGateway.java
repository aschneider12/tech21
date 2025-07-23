package br.com.fiap.restaurante.core.interfaces.gateway;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;

import java.util.List;

public interface IRestauranteGateway {

    Restaurante cadastrar(Restaurante restaurante);

    Restaurante atualizar(Restaurante restaurante);

    boolean deletar(Long id);

    List<Restaurante> buscarTodosRestaurantes();

    Restaurante buscarRestaurantePorIdentificador(Long id);

    Restaurante buscarRestaurantePorNome(String nomeRestaurante);
}
