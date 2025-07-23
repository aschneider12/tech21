package br.com.fiap.restaurante.core.domain.usecases.restaurante;

import br.com.fiap.restaurante.core.interfaces.gateway.IRestauranteGateway;

public class FactoryRestauranteUseCase {

    protected final IRestauranteGateway gateway;

    public FactoryRestauranteUseCase(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public UseCaseBuscarTodosRestaurantes buscarTodosRestaurantes() {
        return UseCaseBuscarTodosRestaurantes.create(gateway);
    }

    public UseCaseBuscarRestaurantePorID buscarRestaurantePorId() {
        return UseCaseBuscarRestaurantePorID.create(gateway);
    }
//
//    public Object deletarRestaurante() {
//    }
}
