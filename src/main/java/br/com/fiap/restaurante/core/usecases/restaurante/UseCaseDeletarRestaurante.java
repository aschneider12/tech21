package br.com.fiap.restaurante.core.usecases.restaurante;

import br.com.fiap.restaurante.domain.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.domain.exceptions.EntidadeJaExisteException;

public class UseCaseDeletarRestaurante {

    private final IRestauranteGateway gateway;

    private UseCaseDeletarRestaurante(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }
    public static UseCaseDeletarRestaurante create(IRestauranteGateway gateway) {
        return new UseCaseDeletarRestaurante(gateway);
    }

    public boolean run(Long id) throws EntidadeJaExisteException {

        return gateway.deletar(id);
    }
}
