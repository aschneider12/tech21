package br.com.fiap.restaurante.core.domain.usecases.restaurante;

import br.com.fiap.restaurante.core.interfaces.IRestauranteGateway;

public class FactoryRestauranteUseCase {

    protected final IRestauranteGateway gateway;

    public FactoryRestauranteUseCase(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public UseCaseBuscarTodosRestaurantes buscarTodosRestaurantes() {
        return new UseCaseBuscarTodosRestaurantes(gateway);
    }

}
