package br.com.fiap.restaurante.core.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.interfaces.gateway.IRestauranteGateway;

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

    public UseCaseDeletarRestaurante deletarRestaurante() {
        return UseCaseDeletarRestaurante.create(gateway);
    }

    public UseCaseCadastrarRestaurante cadastrarRestaurante(){
        return UseCaseCadastrarRestaurante.create(gateway);
    }

    public UseCaseAtualizarRestaurante atualizarRestaurante(){
        return UseCaseAtualizarRestaurante.create(gateway);
    }
}
