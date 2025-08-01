package br.com.fiap.restaurante.application.usecases.restaurante;


import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;

public class UseCaseBuscarRestaurantePorNome {

    private final IRestauranteGateway gateway;

    private UseCaseBuscarRestaurantePorNome(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public static UseCaseBuscarRestaurantePorNome create(IRestauranteGateway gateway) {
        return new UseCaseBuscarRestaurantePorNome(gateway);
    }
    /**
     * O run é o metodo principal de cada use case, ele de fato executa o que
     * foi criado para fazer.
     *
     * @param String nomeRestaurante
     * @return entity Restaurante
     *
     * @throws EntidadeNaoEncontradaException
     */
    public RestauranteOutput run(String nomeRestaurante) throws EntidadeNaoEncontradaException {

        Restaurante restBD = gateway.buscarRestaurantePorNome(nomeRestaurante);

        if (restBD == null)
            throw new EntidadeNaoEncontradaException("Restaurante", nomeRestaurante);

        return RestauranteOutput.fromDomain(restBD);
    }
}
