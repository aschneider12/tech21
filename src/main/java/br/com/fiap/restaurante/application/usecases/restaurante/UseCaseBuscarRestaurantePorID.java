package br.com.fiap.restaurante.application.usecases.restaurante;

import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;

public class UseCaseBuscarRestaurantePorID  {

    private final IRestauranteGateway gateway;

    private UseCaseBuscarRestaurantePorID(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public static UseCaseBuscarRestaurantePorID create(IRestauranteGateway gateway) {
        return new UseCaseBuscarRestaurantePorID(gateway);
    }

    public RestauranteOutput run(Long id) throws EntidadeNaoEncontradaException {

        Restaurante restauranteExistente = gateway.buscarRestaurantePorIdentificador(id);

        if(restauranteExistente == null)
            throw new EntidadeNaoEncontradaException("Restaurante", id.toString());

        return RestauranteOutput.fromDomain(restauranteExistente);
    }
}
