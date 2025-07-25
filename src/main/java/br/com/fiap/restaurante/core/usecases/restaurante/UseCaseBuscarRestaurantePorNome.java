package br.com.fiap.restaurante.core.usecases.restaurante;


import br.com.fiap.restaurante.core.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.core.domain.models.Restaurante;
import br.com.fiap.restaurante.domain.exceptions.EntidadeNaoEncontradaException;

public class UseCaseBuscarRestaurantePorNome extends FactoryRestauranteUseCase {

    protected UseCaseBuscarRestaurantePorNome(IRestauranteGateway gateway) {
        super(gateway);
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
    public Restaurante run(String nomeRestaurante) throws EntidadeNaoEncontradaException {

        Restaurante restBD = gateway.buscarRestaurantePorNome(nomeRestaurante);

        if (restBD == null)
            throw new EntidadeNaoEncontradaException("Restaurante", nomeRestaurante);

        return restBD;
    }
}
