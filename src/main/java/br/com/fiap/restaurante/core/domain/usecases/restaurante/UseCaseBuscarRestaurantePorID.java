package br.com.fiap.restaurante.core.domain.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.core.interfaces.gateway.IRestauranteGateway;

public class UseCaseBuscarRestaurantePorID extends FactoryRestauranteUseCase {

    private UseCaseBuscarRestaurantePorID(IRestauranteGateway gateway) {
        super(gateway); // reutiliza a lógica do construtor
    }

    public static UseCaseBuscarRestaurantePorID create(IRestauranteGateway gateway) {
        return new UseCaseBuscarRestaurantePorID(gateway);
    }

    public Restaurante run(Long id) throws EntidadeNaoEncontradaException {

        Restaurante restauranteExistente = gateway.buscarRestaurantePorIdentificador(id);

        if(restauranteExistente == null)
            throw new EntidadeNaoEncontradaException("Restaurante", id.toString());

        return restauranteExistente;
    }
}
