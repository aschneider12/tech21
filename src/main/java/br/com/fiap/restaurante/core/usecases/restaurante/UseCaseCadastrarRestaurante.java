package br.com.fiap.restaurante.core.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.core.domain.models.Restaurante;
import br.com.fiap.restaurante.domain.exceptions.EntidadeJaExisteException;

public class UseCaseCadastrarRestaurante {

    private final IRestauranteGateway gateway;

    private UseCaseCadastrarRestaurante(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public static UseCaseCadastrarRestaurante create(IRestauranteGateway gateway) {
        return new UseCaseCadastrarRestaurante(gateway);
    }

    public Restaurante run(Restaurante restaurante) throws EntidadeJaExisteException {

        Restaurante restauranteExistente = gateway.buscarRestaurantePorNome(restaurante.getNome());
        if (restauranteExistente != null)
            throw new EntidadeJaExisteException("Restaurante", restaurante.getNome());

        //restauranteCadastrar = RestauranteMapper.toDomain(dto);

        Restaurante cadastrado = gateway.cadastrar(restaurante);

        return cadastrado;
    }

}
