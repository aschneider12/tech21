package br.com.fiap.restaurante.application.usecases.restaurante;

import br.com.fiap.restaurante.application.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;

public class UseCaseDeletarRestaurante {

    private final IRestauranteGateway gateway;

    private UseCaseDeletarRestaurante(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }
    public static UseCaseDeletarRestaurante create(IRestauranteGateway gateway) {
        return new UseCaseDeletarRestaurante(gateway);
    }

    public boolean run(Long restauranteId) throws EntidadeJaExisteException {

        return gateway.deletar(restauranteId);
    }
}
