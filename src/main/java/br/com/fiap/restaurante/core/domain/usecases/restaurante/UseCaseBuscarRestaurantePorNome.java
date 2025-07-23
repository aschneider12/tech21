package br.com.fiap.restaurante.core.domain.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.core.interfaces.gateway.IRestauranteGateway;

/**
 * Realilza o cadastro de um novo restaurante.
 */
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

        Restaurante restauranteExistente = gateway.buscarRestaurantePorNome(nomeRestaurante);

        if(restauranteExistente == null)
            throw new EntidadeNaoEncontradaException("Restaurante", nomeRestaurante);

        return restauranteExistente;
    }
}
